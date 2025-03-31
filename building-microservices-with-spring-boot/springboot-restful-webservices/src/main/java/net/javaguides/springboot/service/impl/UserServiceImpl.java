package net.javaguides.springboot.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.springboot.dto.UserDto;
//import net.javaguides.springboot.dto.mapper.UserMapper;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Override
    public UserDto getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        User existingUser = optionalUser.orElse(null);
//        return UserMapper.toUserDto(existingUser);
        return modelMapper.map(existingUser, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
//        return users.stream().map(UserMapper::toUserDto).toList();
        return users.stream().map(user -> modelMapper.map(user, UserDto.class)).toList();
    }


    @Override
    public UserDto createUser(UserDto userDto) {
//        User userEntity = UserMapper.mapToUser(userDto);
//        User savedUser = userRepository.save(userEntity);

        User userEntity = modelMapper.map(userDto, User.class);
        User savedUser = userRepository.save(userEntity);
//        return UserMapper.toUserDto(savedUser);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto user) {
        Optional<User> optionalExistingUser = userRepository.findById(user.getId());
        if (optionalExistingUser.isPresent()) {
            User existingUser = optionalExistingUser.get();
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setEmail(user.getEmail());
            User savedUser = userRepository.save(existingUser);
//            return UserMapper.toUserDto(savedUser);
            return modelMapper.map(savedUser, UserDto.class);
        }
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
