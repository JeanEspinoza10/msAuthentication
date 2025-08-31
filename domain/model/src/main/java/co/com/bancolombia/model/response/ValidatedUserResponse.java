package co.com.bancolombia.model.response;

import co.com.bancolombia.model.users.UserModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValidatedUserResponse {

    private Boolean success;
    private String message;
    private List<UserData> data;

    public static ValidatedUserResponse success(List<UserData> data) {
        return new ValidatedUserResponse(Boolean.TRUE, "User found", data);
    }

    public static ValidatedUserResponse error(String message) {
        return new ValidatedUserResponse(Boolean.FALSE, message, Collections.emptyList());
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserData {
        private String name;
        private String email;

        public static UserData fromUserModel(UserModel user) {
            return new UserData(
                    user.getName(),
                    user.getEmail()
            );
        }
    }
}
