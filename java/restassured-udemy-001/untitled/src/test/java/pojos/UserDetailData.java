package pojos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDetailData {
    private int id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;
}
