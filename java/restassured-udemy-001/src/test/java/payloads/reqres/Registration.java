package payloads.reqres;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Registration {
    private String username;
    private String email;
    private String password;
}
