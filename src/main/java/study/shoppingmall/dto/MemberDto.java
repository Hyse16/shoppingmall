package study.shoppingmall.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.aspectj.bridge.Message;
import org.hibernate.validator.constraints.Length;
import org.springframework.context.annotation.Primary;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Getter
public class MemberDto {

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    @NotEmpty(message = "이메일 입력은 필수 입력 값입니다.")
    @Email(message = "이메일 형식으로 입력하세요")
    private String email;

    @NotEmpty(message = "비밀번호 입력은 필수 입력 값입니다.")
    @Length(min = 4, max = 16, message = "비밀번호는 최소4자 이상, 최대 16자 이하로 입력해주세요.")
    private String password;

    @NotEmpty(message = "주소는 필수 입력 값입니다.")
    private String address;

    @Builder
    public MemberDto(String name, String email, String password, String address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
    }
}
