package med.voll.api.domain.user;


//DTO which represents login and password data
public record AuthenticationData(
        String login,
        String password) {
}
