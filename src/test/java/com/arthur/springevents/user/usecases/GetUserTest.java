package com.arthur.springevents.user.usecases;

import com.arthur.springevents.user.domain.User;
import com.arthur.springevents.user.repository.UserRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
class GetUserTest {
  @InjectMocks private GetUser getUser;
  
  @Mock private UserRepository repository;
  @Mock private ApplicationEventPublisher eventPublisher;

  @Test
  @DisplayName("Given User exists, when getting it, should return User")
  void givenUserExists_whenGetting_shouldReturnUser() {
    var user = new User();

    given(repository.findById(any()))
      .willReturn(Optional.of(user));

    getUser.execute(user.getId());

    then(repository)
      .should(times(1))
      .findById(user.getId());
  }

  @Test
  @DisplayName("Given User does not exist, when getting it, should throw")
  void givenUserDoesNotExist_whenGetting_shouldThrow() {
    given(repository.findById(any()))
      .willReturn(Optional.empty());

    var userId = UUID.randomUUID();
    assertThrows(EntityNotFoundException.class, () -> getUser.execute(userId));
  }
}
