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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class CreateUserTest {
  @InjectMocks private CreateUser createUser;

  @Mock private UserRepository repository;
  @Mock private ApplicationEventPublisher eventPublisher;

  @Test
  @DisplayName("Should create a new one and save to database")
  void shouldCreateAndSave() {

    createUser.execute();

    then(repository)
      .should(times(1))
      .save(any(User.class));
  }
}
