package codesquad.web;

import codesquad.web.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private List<User> users = new ArrayList<>();

    @PostMapping("/users")
    public String create(User user, Model model) {
        users.add(user);
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String list(Model model) {
        model.addAttribute("users", users);
        return "/user/list";
    }

    @GetMapping("/users/{index}")
    public String show(@PathVariable int index, Model model) {
        model.addAttribute("users", users.get(index));
        return "/user/profile";
    }

    @GetMapping("/users/{index}/form")
    public String updateForm(@PathVariable int index,  Model model) {
        model.addAttribute("user", users.get(index));
        return "/user/updateForm";
    }

    @PostMapping("/users/{index}/update")
    public String update(@PathVariable int index, User user){
        users.set(index, user);
        return "redirect:/users";
    }
}
