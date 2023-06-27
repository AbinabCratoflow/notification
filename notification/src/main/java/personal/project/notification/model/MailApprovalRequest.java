package personal.project.notification.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailApprovalRequest {
    private String to;
    private String userName;
    private String documentNumber;
    private String accountCoding;
    private String documentType;
    private String documentAmount;
    private String urn;
    private String location;
    private String vendorName;
    private String url;
}
