package com.xl0e.nutric.web.services;

import com.ivaga.tapestry.csscombiner.CssCombinerModule;
import com.ivaga.tapestry.csscombiner.LessModule;
import com.xl0e.hibernate.utils.EntityFilterBuilder;
import com.xl0e.nutric.dao.AccountDao;
import com.xl0e.nutric.model.Account;
import com.xl0e.tapestry.hibernate.HibernateModule;
import com.xl0e.util.CryptoUtils;

import org.apache.tapestry5.ComponentParameterConstants;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.ImportModule;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.InjectService;
import org.apache.tapestry5.ioc.services.ApplicationDefaults;
import org.apache.tapestry5.ioc.services.FactoryDefaults;
import org.apache.tapestry5.security.AuthenticationService;
import org.apache.tapestry5.security.LoginResult;
import org.apache.tapestry5.security.api.AccessAttributes;
import org.apache.tapestry5.security.api.AuthenticationHandler;
import org.apache.tapestry5.security.api.CookieCredentialEncoder;
import org.apache.tapestry5.security.api.Credentials;
import org.apache.tapestry5.security.api.User;
import org.apache.tapestry5.security.api.UserProvider;
import org.apache.tapestry5.security.impl.CookieCredentials;
import org.apache.tapestry5.security.impl.UsernamePasswordCredentials;
import org.apache.tapestry5.services.ApplicationStateManager;
import org.apache.tapestry5.services.AssetSource;
import org.apache.tapestry5.services.javascript.ExtensibleJavaScriptStack;
import org.apache.tapestry5.services.javascript.JavaScriptStack;
import org.apache.tapestry5.services.javascript.JavaScriptStackSource;
import org.apache.tapestry5.web.services.modules.CoreWebappModule;
import org.apache.tapestry5.web.services.security.CookieEncryptorDecryptor;
import org.apache.tapestry5.web.services.security.SecuredAnnotationModule;

@ImportModule({
        CoreWebappModule.class,
        LessModule.class,
        CssCombinerModule.class,
        HibernateModule.class,
        SecuredAnnotationModule.class
})
public class AppModule {

    @FactoryDefaults
    public static void contributeFactoryDefaults(MappedConfiguration<String, Object> configuration) {
        configuration.override(SymbolConstants.APPLICATION_VERSION, "1.0-SNAPSHOT");
        configuration.override(SymbolConstants.HMAC_PASSPHRASE, "asf3423*&%6234234kjhakdf325243");
        configuration.override(ComponentParameterConstants.GRID_TABLE_CSS_CLASS, "table table-responsive");
    }

    @ApplicationDefaults
    public static void contributeApplicationDefaults(MappedConfiguration<String, Object> configuration) {
        configuration.add(SymbolConstants.FORM_CLIENT_LOGIC_ENABLED, false);
        configuration.add(SymbolConstants.SECURE_ENABLED, true);
        configuration.add(SymbolConstants.SUPPORTED_LOCALES, "ru,en");
        configuration.add(SymbolConstants.GZIP_COMPRESSION_ENABLED, "false");
        configuration.add(SymbolConstants.CHARSET, "utf-8");
        configuration.add(SymbolConstants.COMBINE_SCRIPTS, true);
        configuration.add(SymbolConstants.ENABLE_HTML5_SUPPORT, true);
        configuration.add(SymbolConstants.JAVASCRIPT_INFRASTRUCTURE_PROVIDER, "jquery");
        // configuration.add(SymbolConstants.BOOTSTRAP_ROOT, "context:/static/bootstrap-3.3.6-dist");
        configuration.add(SymbolConstants.MINIFICATION_ENABLED, true);
    }

    public static void bind(ServiceBinder binder) {
        binder.bind(JavaScriptStack.class, ExtensibleJavaScriptStack.class).withId("AppJavaScriptStack");
        // binder.bind(JSSupport.class);
    }

    // @Decorate(serviceInterface = JavaScriptStackSource.class)
    // public JavaScriptStackSource decorateJavaScriptStackSource(JavaScriptStackSource original) {
    // return new JavaScriptStackSourceWrapper(original);
    // }

    @Contribute(JavaScriptStackSource.class)
    public static void contributeJavaScriptStackSource(MappedConfiguration<String, JavaScriptStack> map,
                                                       AssetSource assetSource,
                                                       @InjectService("AppJavaScriptStack") JavaScriptStack appJavaScriptStack) {
        map.add("app-core", appJavaScriptStack);
    }

    public static UserProvider<Account, Credentials> buildUserProvider(@Inject final AccountDao accountDao,
                                                                       @Inject final CookieEncryptorDecryptor cookieEncryptorDecryptor) {
        return c -> {
            if (c instanceof UsernamePasswordCredentials) {
                UsernamePasswordCredentials credentials = (UsernamePasswordCredentials) c;
                String username = CryptoUtils.createSHA512String(credentials.getUsername());
                String password = CryptoUtils.createSHA512String(credentials.getPassword());
                return accountDao.findOneByFilter(EntityFilterBuilder.and()
                        .eq("usernameHash", username)
                        .eq("passwordHash", password));
            }
            if (c instanceof CookieCredentials) {
                CookieCredentials credentials = (CookieCredentials) c;
                String[] usernameAndPassword = cookieEncryptorDecryptor.decryptArray(credentials.getValue());
                return accountDao.findOneByFilter(EntityFilterBuilder.and()
                        .eq("usernameHash", usernameAndPassword[0])
                        .eq("passwordHash", usernameAndPassword[1]));
            }
            return null;
        };
    }

    public static CookieCredentialEncoder<Account> buildCookieCredentialEncoder(@Inject final CookieEncryptorDecryptor cookieEncryptorDecryptor) {
        return user -> {
            String value = cookieEncryptorDecryptor.encryptArray(user.getUsernameHash(), user.getPasswordHash());
            return new CookieCredentials(value);
        };
    }

    @Contribute(AuthenticationService.class)
    public void contributeAuthenticationService(OrderedConfiguration<AuthenticationHandler> configuration, ApplicationStateManager applicationStateManager) {
        configuration.add("SetUserInSessionHandler", new AuthenticationHandler() {

            @Override
            public void handleLogin(LoginResult loginResult) {
                User user = loginResult.getUser();
                if (loginResult.isSuccess() && null != user) {
                    applicationStateManager.set(Account.class, (Account) user);
                }
            }

            @Override
            public void handleLogout(AccessAttributes user) {
            }
        }, "after:*");
    }

}
