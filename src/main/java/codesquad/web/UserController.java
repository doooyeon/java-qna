package codesquad.web;

import codesquad.web.domain.User;
import codesquad.web.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public String create(User user, Model model) {
        userRepository.save(user);
        return "redirect:/users";
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "/user/list";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable long id, Model model) {
        User user = userRepository.findById(id).get();
        model.addAttribute("user", user);
        return "/user/profile";
    }

    @GetMapping("/{id}/form")
    public String updateForm(@PathVariable long id,  Model model) {
        User user = userRepository.findById(id).get();
        model.addAttribute("user", user);
        return "/user/updateForm";
    }

    @PutMapping("/{id}/update")
    public String update(@PathVariable long id, User newUser){
        User user = userRepository.findById(id).get();
        user.setPassword(newUser.getPassword());
        user.setName(newUser.getName());
        user.setEmail(newUser.getEmail());
        userRepository.save(user);
        return "redirect:/users";
    }
}
