package ca.SheridanCollege.Assignment3.Controllers;
;
import ca.SheridanCollege.Assignment3.Beans.Sessioninfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class mainController {


    @GetMapping("/")
    public String getHome(HttpSession session,Model model, @ModelAttribute Sessioninfo message){
        List<String> messageList = new ArrayList<>();
        if(session.isNew()) {
            session.setAttribute("messageList", messageList);
        }

        model.addAttribute("message", new Sessioninfo() {
        });

       return"index";
    }

    @PostMapping("/addMessage")
    public String addAvenger(@ModelAttribute Sessioninfo message, Model model, HttpSession session) {



        List<Sessioninfo> messageList = (List<Sessioninfo>)session.getAttribute("messageList");
        if(session.isNew()){
            messageList.clear();

        }

        messageList.add(message);
        model.addAttribute("message", new Sessioninfo());


        return "index";
    }
    @GetMapping("/destroySession")
    public String destroySession(HttpSession session){
        session.invalidate();
        return "index";

    }


}
