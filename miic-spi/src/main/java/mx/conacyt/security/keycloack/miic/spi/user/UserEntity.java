/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates
 * and other contributors as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package mx.conacyt.security.keycloack.miic.spi.user;

import java.time.Instant;
import java.util.Locale;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@NamedQueries({ @NamedQuery(name = "getUserByUsername", query = "select u from UserEntity u where u.login = :login"),
		@NamedQuery(name = "getUserByEmail", query = "select u from UserEntity u where u.email = :email"),
		@NamedQuery(name = "getUserCount", query = "select count(u) from UserEntity u"),
		@NamedQuery(name = "getAllUsers", query = "select u from UserEntity u"),
		@NamedQuery(name = "searchForUser", query = "select u from UserEntity u where "
				+ "( lower(u.login) like :search or u.email like :search ) order by u.login"), })
@Entity
@Table(name = "jhi_user")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
	@SequenceGenerator(name = "sequenceGenerator")
	private Long id;

	@Column(length = 50, unique = true, nullable = false)
	private String login;

	@Column(name = "password_hash", length = 60, nullable = false)
	private String password;

	@Column(name = "first_name", length = 50)
	private String firstName;

	@Column(name = "last_name", length = 50)
	private String lastName;

	@Column(length = 254, unique = true)
	private String email;

	@Column(nullable = false)
	private boolean activated = false;

	@Column(name = "lang_key", length = 10)
	private String langKey;

	@Column(name = "image_url", length = 256)
	private String imageUrl;

	@Column(name = "activation_key", length = 20)
	@JsonIgnore
	private String activationKey;

	@Column(name = "reset_key", length = 20)
	@JsonIgnore
	private String resetKey;

	@Column(name = "reset_date")
	private Instant resetDate = null;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	// Lowercase the login before saving it in database
	public void setLogin(String login) {
		this.login = lowerCase(login, Locale.ENGLISH);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public boolean getActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public String getActivationKey() {
		return activationKey;
	}

	public void setActivationKey(String activationKey) {
		this.activationKey = activationKey;
	}

	public String getResetKey() {
		return resetKey;
	}

	public void setResetKey(String resetKey) {
		this.resetKey = resetKey;
	}

	public Instant getResetDate() {
		return resetDate;
	}

	public void setResetDate(Instant resetDate) {
		this.resetDate = resetDate;
	}

	public String getLangKey() {
		return langKey;
	}

	public void setLangKey(String langKey) {
		this.langKey = langKey;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof UserEntity)) {
			return false;
		}
		return id != null && id.equals(((UserEntity) o).id);
	}

	@Override
	public int hashCode() {
		return 31;
	}

	@Override
	public String toString() {
		return "User{" + "login='" + login + '\'' + ", firstName='" + firstName + '\'' + ", lastName='" + lastName
				+ '\'' + ", email='" + email + '\'' + ", imageUrl='" + imageUrl + '\'' + ", activated='" + activated
				+ '\'' + ", langKey='" + langKey + '\'' + ", activationKey='" + activationKey + '\'' + "}";
	}

	private static String lowerCase(final String str, final Locale locale) {
		if (str == null) {
			return null;
		}
		return str.toLowerCase(locale);
	}
}
