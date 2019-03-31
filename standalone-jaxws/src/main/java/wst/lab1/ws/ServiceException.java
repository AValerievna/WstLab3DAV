package wst.lab1.ws;

import lombok.Getter;

import javax.xml.ws.WebFault;

@WebFault(faultBean = "wst.lab1.ws.ServiceFault")
public class ServiceException extends Exception {
    @Getter
    private final ServiceFault faultInfo;

    public ServiceException(String message, ServiceFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    public ServiceException(String message, Throwable cause, ServiceFault faultInfo) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }
}
