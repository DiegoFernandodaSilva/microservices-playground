package com.github.diegofernandodasilva.covid19tracker.audit.context;

import com.github.diegofernandodasilva.covid19tracker.security.ContextProvider;
import com.github.diegofernandodasilva.covid19tracker.security.model.User;
import com.github.diegofernandodasilva.microservices.playground.auditLog.AuditLogContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
public class Covid19TrackerAuditLogContextProvider implements AuditLogContextProvider {

    private final ContextProvider<User> loggedUserContextProvider;

    @Autowired
    public Covid19TrackerAuditLogContextProvider(ContextProvider<User> loggedUserContextProvider) {
        this.loggedUserContextProvider = loggedUserContextProvider;
    }

    @Override
    public AuditLogContext getAuditLogContext() {
        return new AuditLogContext(getLoggedUser(), getIpAddressFromRequest());
    }

    @Override
    public boolean isSuitable() {
        return loggedUserContextProvider.get() != null;
    }

    private String getLoggedUser() {
        return loggedUserContextProvider.get().getUserName();
    }

    private String getIpAddressFromRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();

        return request.getRemoteAddr();
    }


}
