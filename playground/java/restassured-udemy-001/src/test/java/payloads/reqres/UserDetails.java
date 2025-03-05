package payloads.reqres;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDetails {
    private UserDetailData data;
    private UserDetailSupport support;
}
