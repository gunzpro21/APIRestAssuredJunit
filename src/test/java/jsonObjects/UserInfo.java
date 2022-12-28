package jsonObjects;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.javafaker.Faker;

import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * <h1>Add a ping!</h1> AuthPayload contains a json or data object 
 * It use lombok library to create constructor codes
 * @author Joe Phan | email: grenade.eminem@gmail.com
 * @since 2022 Oct 14th
 */

@NoArgsConstructor
@ToString
public class UserInfo {
	@JsonProperty
	public String name;

	@JsonProperty
	public String job;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserInfo other = (UserInfo) obj;
		return Objects.equals(job, other.job) && Objects.equals(name, other.name);
	}

	public UserInfo(Faker faker) {
		this.name = faker.name().firstName();
		this.job = faker.job().title();
	}
}
