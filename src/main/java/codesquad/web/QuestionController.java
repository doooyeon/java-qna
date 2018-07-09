package codesquad.web;

import codesquad.web.domain.Question;
import codesquad.web.domain.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class QuestionController {
    @Autowired
    private QuestionRepository questionRepository;

    @PostMapping("/questions")
    public String create(Question question) {
        questionRepository.save(question);
        return "redirect:/";
    }

    @GetMapping("/")
    public String list(Model model){
        model.addAttribute("questions", questionRepository.findAll());
        return "/index";
    }

    @GetMapping("/questions/{id}")
    public String show(@PathVariable long id, Model model){
        Question question = questionRepository.findById(id).get();
        model.addAttribute("question", question);
        return "/qna/show";
    }

    @GetMapping("/questions/{id}/form")
    public String updateForm(@PathVariable long id, Model model) {
        Question question = questionRepository.findById(id).get();
        model.addAttribute("question", question);
        System.out.println("came into updateForm");
        return "/qna/updateForm";
    }

    @PutMapping("/questions/{id}/update")
    public String update(@PathVariable long id, Question newQuestion) {
        Question question = questionRepository.findById(id).get();
        question.setTitle(newQuestion.getTitle());
        question.setWriter(newQuestion.getWriter());
        question.setContents(newQuestion.getContents());
        questionRepository.save(question);
        return "redirect:/";
    }
    @DeleteMapping("/questions/{id}")
    public String delete(@PathVariable long id){
        Question question = questionRepository.findById(id).get();
        questionRepository.delete(question);
        return "redirect:/";
    }
}
