package app.module.admin.usecase.email.send.adapters.web;

import java.io.IOException;
import javax.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import app.module.admin.usecase.email.send.adapters.web.model.EmailVO;
import app.module.admin.usecase.email.send.ports.application.domain.Email;
import app.module.admin.usecase.email.send.ports.EmailSendPortIn;
import app.module.core.config.application.util.MessageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class EmailSendController {
  private final EmailSendPortIn emailSendPortIn;
  private final MessageUtil messageUtil;

  @PostMapping(path = "/addons/email/form")
  public String sendEmail(
      RedirectAttributes redirectAttributes,
      @ModelAttribute("formDto") EmailVO emailVO,
      BindingResult bindingResult)
      throws IOException, MessagingException {

    if (bindingResult.hasErrors()) {
      return "email/emailForm";
    }

    emailSendPortIn.connect(
        new Email(
            emailVO.getTo(),
            null,
            emailVO.getSubject(),
            emailVO.getMsgType(),
            emailVO.getMessage(),
            emailVO.getMultipartFiles()));

    redirectAttributes.addFlashAttribute(
        "operationMessage", messageUtil.getMessage("operation.common.email.to", emailVO.getTo()));

    return "redirect:/addons/email/form";
  }
}
