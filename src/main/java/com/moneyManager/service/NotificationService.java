package com.moneyManager.service;

import com.moneyManager.dto.ExpenseDTO;
import com.moneyManager.entity.ProfileEntity;
import com.moneyManager.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final ProfileRepository profileRepository;
    private final EmailService emailService;
    private final ExpenseService expenseService;

    @Value("${money.manager.frontend.url}")
    private String frontendUrl;

    @Scheduled(cron = "0 0 22 * * *",zone = "IST")
    public void sendDailyIncomeExpenseReminder(){
        log.info("Job Started : senddailyIncomeExpense");
        List<ProfileEntity> profiles = profileRepository.findAll();
        for(ProfileEntity profile : profiles){
            String body = "Hi " + profile.getFullName() + ", <br><br>"
                    + "This is a friendly reminder to add your income and expenses to Money Manager App."
                    + "<br><br>"
                    + "<a href='" + frontendUrl
                    + "' style='display:inline-block;padding:10px 20px;"
                    + "background-color:#4CAF50;color:white;text-decoration:none;"
                    + "border-radius:5px;'>Open Money Manager</a>"
                    + "<br><br>Best regards,<br>Money Manager Team";
            emailService.sendEmail(profile.getEmail(),"Daily reminder:Add your income and expenses",body);
        log.info("Job Completed");
        }
    }
    @Scheduled(cron = "0 0 23 * * *",zone = "IST")
    public void sendDailyExpenseSummary(){
        log.info("Job Summmary started");
        List<ProfileEntity> profiles = profileRepository.findAll();
        for(ProfileEntity profile : profiles) {
            List<ExpenseDTO> todaysExpenses = expenseService.getExpensesForUserForDate(profile.getId(), LocalDate.now(ZoneId.of("Asia/Kolkata")));
            if (!todaysExpenses.isEmpty()) {
                StringBuilder table=new StringBuilder();
                table.append("<table style='border-collapse:collapse;width:100%;'>");

                table.append("<tr style='background-color:#f2f2f2;'>"
                        + "<th style='border:1px solid solid #ddd;padding:8px;text-align:left;'>Date</th>"
                        + "<th style='border:1px solid #ddd;padding:8px;text-align:left;'>Category</th>"
                        + "<th style='border:1px solid #ddd;padding:8px;text-align:left;'>Amount</th>"
                        + "<th style='border:1px solid #ddd;padding:8px;text-align:left;'>Description</th>"
                        + "</tr>");
                int i= 1;
                for (ExpenseDTO expenseDTO:todaysExpenses){
                    table.append("<tr>");
                    table.append("<td style='border:1px solid black; padding:8px;'>").append(i++).append("</td>");
                    table.append("<td style='border:1px solid black; padding:8px;'>").append(expenseDTO.getName()).append("</td>");
                    table.append("<td style='border:1px solid black; padding:8px;'>").append(expenseDTO.getAmount()).append("</td>");
                    table.append("<td style='border:1px solid black; padding:8px;'>").append(expenseDTO.getCategoryId()!=null?expenseDTO.getCategoryId():"N/A").append("</td>");
                    table.append("</tr>");
                }
                table.append("</table>");
                String body = "Hi " + profile.getFullName() + ", <br><br>"
                        + "This is a summary of your income and expenses to Money Manager App."
                        + "<br><br>"+table+
                         "<br><br>Best regards,<br>Money Manager Team";
                emailService.sendEmail(profile.getEmail(),"Your daily expense summary.",body);
            }
        }
        log.info("Job Summmary completed");

        }
}
