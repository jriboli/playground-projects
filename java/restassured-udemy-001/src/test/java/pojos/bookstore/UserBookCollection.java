package pojos.bookstore;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserBookCollection {

    private String userId;
    private List<ISBN> collectionOfIsbns;
}
