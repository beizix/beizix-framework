package org.beizix.admin.usecase.email.send.adapter.web;

import java.io.IOException;
import javax.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.email.send.application.domain.Email;
import org.beizix.admin.usecase.email.send.application.port.in.EmailSendPortIn;
import org.beizix.core.config.application.util.MessageUtil;
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
      @ModelAttribute("formDto") EmailDto formDto,
      BindingResult bindingResult)
      throws IOException, MessagingException {

    if (bindingResult.hasErrors()) {
      return "email/emailForm";
    }

    emailSendPortIn.connect(
        new Email(
            formDto.getTo(),
            null,
            formDto.getSubject(),
            formDto.getMsgType(),
            formDto.getMessage(),
            formDto.getMultipartFiles()));

    redirectAttributes.addFlashAttribute(
        "operationMessage", messageUtil.getMessage("operation.common.email.to", formDto.getTo()));

    return "redirect:/addons/email/form";
  }
}
