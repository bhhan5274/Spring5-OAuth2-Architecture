package io.github.bhhan.oauth2.federated.server.authorization.config.user;

import io.github.bhhan.oauth2.federated.server.authorization.config.provider.ProviderType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String userId;
    private String username;
    private String password;
    private String email;
    private String emailVerifiedYn;
    private String profileImageUrl;
    private ProviderType providerType;
    private RoleType roleType;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public User(
            String userId,
            String username,
            String email,
            String emailVerifiedYn,
            String profileImageUrl,
            ProviderType providerType,
            RoleType roleType,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt
    ) {
        this.userId = userId;
        this.username = username;
        this.password = "NO_PASS";
        this.email = email != null ? email : "NO_EMAIL";
        this.emailVerifiedYn = emailVerifiedYn;
        this.profileImageUrl = profileImageUrl != null ? profileImageUrl : "";
        this.providerType = providerType;
        this.roleType = roleType;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", emailVerifiedYn='" + emailVerifiedYn + '\'' +
                ", profileImageUrl='" + profileImageUrl + '\'' +
                ", providerType=" + providerType +
                ", roleType=" + roleType +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                '}';
    }
}

