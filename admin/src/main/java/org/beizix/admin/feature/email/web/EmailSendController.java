package org.beizix.admin.feature.email.web;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.beizix.core.feature.email.application.model.Email;
import org.beizix.core.feature.email.application.service.EmailSendService;
import org.beizix.utility.common.MessageUtil;

import javax.mail.MessagingException;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class EmailSendController {
  private final EmailSendService emailSendService;
  private final MessageUtil messageUtil;
  private final ModelMapper modelMapper;

  @PostMapping(path = "/addons/email/form")
  public String sendEmail(
      RedirectAttributes redirectAttributes,
      @ModelAttribute("formDto") EmailDto formDto,
      BindingResult bindingResult)
      throws IOException, MessagingException {

    if (bindingResult.hasErrors()) {
      return "email/emailForm";
    }

    emailSendService.operate(modelMapper.map(formDto, Email.class));

    redirectAttributes.addFlashAttribute(
        "operationMessage", messageUtil.getMessage("operation.common.email.to", formDto.getTo()));

    return "redirect:/addons/email/form";
  }
}
