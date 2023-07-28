/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.utils;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import sample.dto.OrderDTO;
import sample.dto.ProductDTO;
import sample.dto.UserDTO;
//import javax.activation.DataHandler;
//import javax.activation.DataSource;
//import jdk.javadoc.internal.doclets.toolkit.Content;

/**
 *
 * @author haong
 */
public class MailService {

    //Email: birdmealordersystem@gmail.com
    //pass: kgmnxkwcfipzyyow
    
    //Email: birdmealordershop@gmail.com
    //pass: ejuhumgmoyfwompr
    private static final String FROM = "birdmealordershop@gmail.com";
    private static final String PASSWORD = "ejuhumgmoyfwompr";

    public boolean sendVerifyEmail(String email, String link) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMPT HOST
        props.put("mail.smtp.port", "587"); //TLS 587 SSL465
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Create authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM, PASSWORD);
            }
        };
        Session session = Session.getInstance(props, auth);
        MimeMessage msg = new MimeMessage(session);
        try {
            //Kiểu nội dung
            msg.addHeader("Content-type", "text/html; charset=UTF-8");
            //Ng gửi
            msg.setFrom(FROM);

            //Ng nhận
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));

            //Tiêu đề
            msg.setSubject("Confirm your email address");

            //Quy định email nhận phản hồi
            //msg.setReplyTo()
            //Nội dung
            msg.setText("<div>Thanks for signing up. To complete your account registration " + link + "."
                    + "The link will expire in 15 minutes..</div>", "UTF-8", "html");
            msg.setText("<div>Hello,</div>"
                    + "<br>"
                    + "<div>Thanks for signing up. To complete your account registration " + link + ". "
                    + "The link will expire in 15 minutes.</div>"
                    + "<br>"
                    + "<div>If you did not make this request, ignore it.</div>"
                    + "<br>"
                    + "<div>Thanks!</div>"
                    + "<div>Bird Meal Order Shop</div>", "UTF-8", "html");
            //Gửi email
            Transport.send(msg);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean sendResetPasswordEmail(String email, String link) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMPT HOST
        props.put("mail.smtp.port", "587"); //TLS 587 SSL465
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Create authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM, PASSWORD);
            }
        };
        Session session = Session.getInstance(props, auth);
        MimeMessage msg = new MimeMessage(session);
        try {
            //Kiểu nội dung
            msg.addHeader("Content-type", "text/html; charset=UTF-8");
            //Ng gửi
            msg.setFrom(FROM);

            //Ng nhận
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));

            //Tiêu đề
            msg.setSubject("Password Reset Request");

            //Quy định email nhận phản hồi
            //msg.setReplyTo()
            //Nội dung
            msg.setText("<div>Hello,</div>"
                    + "<br>"
                    + "<div>You recently requested to reset your password. " + link + " to change your password. The link will expire in 15 minutes</div>"
                    + "<br>"
                    + "<div>If you did not make the request to reset your password or if you are in need of further assistance, please contact our Customer Service Team at birdmealordersystem@gmail.com</div>"
                    + "<br>"
                    + "<div>Thanks!</div>"
                    + "<div>Bird Meal Order Shop</div>", "UTF-8", "html");
            //Gửi email
            Transport.send(msg);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean sendEmailConfirmOrderToCustomer(OrderDTO orderDTO, UserDTO userDTO) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMPT HOST
        props.put("mail.smtp.port", "587"); //TLS 587 SSL465
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        //calculate total and subtotal
        double subtotal = 0;
        double total = 0;
        int quantity = 0;
        for (ProductDTO product : orderDTO.getProductsList()) {
            subtotal += product.getPrice() * product.getQuantity();
            quantity += product.getQuantity();
        }
        total = subtotal + orderDTO.getShippingFee();
        // Create authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM, PASSWORD);
            }
        };
        Session session = Session.getInstance(props, auth);
        MimeMessage msg = new MimeMessage(session);
        try {
            //Kiểu nội dung
            msg.addHeader("Content-type", "text/html; charset=UTF-8");
            //Ng gửi
            msg.setFrom(FROM);

            //Ng nhận
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userDTO.getEmail(), false));

            //Tiêu đề
            msg.setSubject("Your order has been confirmed");

            //Quy định email nhận phản hồi
            //msg.setReplyTo()
            //Nội dung
            msg.setText("<div align=\"center\">\n"
                    + "    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:730px\">\n"
                    + "        <tbody>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#E8E8E8\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td bgcolor=\"#F8F8F8\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#E8E8E8\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#D1D1D1\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#FFF\">\n"
                    + "                    <table lang=\"header\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\" style=\"width:100%\">\n"
                    + "                        <tbody>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"70\" valign=\"middle\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"background:#ffffff;height:70\">\n"
                    + "                                    <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" height=\"70\" border=\"0\">\n"
                    + "                                        <tbody>\n"
                    + "                                          <td width=\"100px\"></td>"
                    + "                                            <td align=\"\"\n"
                    + "                                                style=\"font-family: 'Raleway', sans-serif; font-size:34px; color:#499B14; line-height:24px; font-weight: bold; letter-spacing: 1px;  padding-top: 30px;\">\n"
                    + "                                                <span><img\n"
                    + "                                                        src=\"https://lh3.googleusercontent.com/pw/AIL4fc-er2uf7hkMZyYkuvSFfPF7QX9cLbjBt1ACdCa4gc4P0yUicrt-4JvvRI9Rl07p4hbmUDiz6qjMLfINSxuRFJJ503WBWB4NCHgTcrCEN3fuSs4FvD-ueJUqI1agFyQF_1Qrsrh3knuzwbxpC7xSCuGzEpwolK9EkyQH2fJBmWxG_4MGqqb_dLvAIxaOvRnoyeybKKojoSa8PZRv3wPQFNhz0p2JZZpnHzbYyalIzzyClgrL4HX59ZBGEMKKTx4M-4xcPtXhBD-iBDen_xyzhqC_Ba_lXx2OJ3IYyGxeYgIkmBffxv6eJ01dwF54YoULwyajlagTRSQX85Ecv6KM0PQJXaMzhJoGvdA9CISDTxQcV6QTeePNNYH3qedqpKby5zwbc3jqU6J-9DTpeemo3PHw73KYkRH0GLnktP4cmQottwxw8HMwiwG7wBFNb9fbdgDvi1TCf5d1V-XQDY1pbY7eQUb2zqGcVuIi6U00SONfqTrnBtHWIojoDDmyNvJ5PIzLJuNHKD5uz84FHHjEfqcVf1TZV7RczBKwga5h2qat2zWHjy_bl3n3CqIWCOEwUoGRBk2RRXOLzk3KnNKKiv2kRHkXj8N-Ststw0aJZBIhpaUtU8BFjC1-MMsB72b-xYPiYCZ1DImSjT7DDU9DynyhUyoiv1JWKBAORtKV2WWZJk4f87ukwUwSNWUImJjwWy7-fkUEsNpDqxjC8HVNlCCtLce1w7wqLONq24OhMT4TwsGGNkT7Ell8p4zeVYSEOmdRyhiEjmQRZSPTB0b_-Sj2TPCBFXL8FwbiVmQFaixa2hB62bdNamsI0ZEuoRID6dd-QpyNv0tJU9e9UaN2Te199q5qx565y0xrJ5Xgwl6Mt4Y9BteFmgSsgyaHsmvatKJObzuQ2Mqu4RBiiQZgnXA3dLG6LJTivalH6kaGTQVc5a9B7I8-Io__SZk=s250-k-rw-no\"\n"
                    + "                                                        alt=\"icon\"\n"
                    + "                                                        style=\"width:100%;max-width:79px;vertical-align:middle\"></span>\n"
                    + "                                                Bird Meal Order Shop\n"
                    + "                                            </td>\n"
                    + "                                        </tbody>\n"
                    + "                                    </table>\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td height=\"18\"></td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td align=\"center\"\n"
                    + "                                    style=\"font-family: 'Raleway', sans-serif; font-size:22px; font-weight: bold; color:#2a3a4b;\">\n"
                    + "                                    Thank you for your order!</td>\n"
                    + "                            </tr>\n"
                    + "                        </tbody>\n"
                    + "                    </table>\n"
                    + "                    <table lang=\"header\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\" style=\"width:100%\">\n"
                    + "                        <tbody>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"70\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"padding:20px 30px 30px 30px;background:#ffffff;height:70px\">\n"
                    + "                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" height=\"70\" border=\"0\"\n"
                    + "                                        style=\"width:100%;height:70px\">\n"
                    + "                                        <tbody>\n"
                    + "                                            <tr>\n"
                    + "                                                <td>\n"
                    + "                                                    <h3>Hello " + userDTO.getFullName() + ",</h3>\n"
                    + "                                                    <p>Your order #" + orderDTO.getOrderID() + " has been received and is being processed. We\n"
                    + "                                                        will\n"
                    + "                                                        notify you\n"
                    + "                                                        when the parcel is ready.</p>\n"
                    + "                                                </td>\n"
                    + "                                        </tbody>\n"
                    + "                                    </table>\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"30\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"margin-top: 30px;background:#f0f0f0;height: 9.6px\">\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                        </tbody>\n"
                    + "                    </table>\n"
                    + "                    <!-- START DELIVERY DETAILS -->\n"
                    + "                    <table lang=\"header\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\" style=\"width:100%\">\n"
                    + "                        <tbody>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"70\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"padding:30px;background:#ffffff;height:70px\">\n"
                    + "                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" height=\"70\" border=\"0\"\n"
                    + "                                        style=\"width:100%;height:70px\">\n"
                    + "                                        <tbody>\n"
                    + "                                            <tr>\n"
                    + "                                                <td\n"
                    + "                                                    style=\"font-family: 'Raleway', sans-serif; font-size:20px; color:#2a3a4b;\">\n"
                    + "                                                    <span><img\n"
                    + "                                                            src=\"https://ci6.googleusercontent.com/proxy/AUmakQI121QQ_WvdspszI-42jmht-u29A4iIvgfX5V-q3Z9qyUAfpUFNiqpGKP4CW6L1x8cnoxHpjGrhiuLmDr8J92EgI9gpuNCZdjW70wzcGuk=s0-d-e1-ft#https://img.alicdn.com/tfs/TB1ciNPybj1gK0jSZFOXXc7GpXa-48-48.png\"\n"
                    + "                                                            alt=\"icon\"\n"
                    + "                                                            style=\"height: 29px; vertical-align:middle\"></span>\n"
                    + "                                                    <div style=\"display: inline-block; margin-left: 7px;\">Delivery\n"
                    + "                                                        details</div>\n"
                    + "                                                </td>\n"
                    + "                                            </tr>\n"
                    + "                                            <tr>\n"
                    + "                                                <td height=\"15\"></td>\n"
                    + "                                            </tr>\n"
                    + "                                            <tr>\n"
                    + "                                                <td>\n"
                    + "                                                    <table cellpadding=\"2\" cellspacing=\"0\" width=\"100%\">\n"
                    + "                                                        <tbody>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" valign=\"top\" style=\"font-weight:bold\">\n"
                    + "                                                                    Name:</td>\n"
                    + "                                                                <td width=\"75%\" valign=\"top\">" + orderDTO.getFullName() + "</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td style=\"font-weight:bold\">Address:</td>\n"
                    + "                                                                <td>" + orderDTO.getOrderAddress() + "</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td valign=\"top\" style=\"font-weight:bold\">Phone:\n"
                    + "                                                                </td>\n"
                    + "                                                                <td valign=\"top\">" + orderDTO.getPhoneNumber() + "</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                        </tbody>\n"
                    + "                                                    </table>\n"
                    + "                                                </td>\n"
                    + "                                            </tr>\n"
                    + "                                        </tbody>\n"
                    + "                                    </table>\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"30\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"margin-top: 30px;background:#f0f0f0;height: 9.6px\">\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                        </tbody>\n"
                    + "                    </table>\n"
                    + "                    <!-- END DELIVERY DETAILS -->\n"
                    + "                    <!-- START ORDER DETAIL -->\n"
                    + "                    <table lang=\"header\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\" style=\"width:100%\">\n"
                    + "                        <tbody>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"70\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"padding:30px;background:#ffffff;height:70px\">\n"
                    + "                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" height=\"70\" border=\"0\"\n"
                    + "                                        style=\"width:100%;height:70px\">\n"
                    + "                                        <tbody>\n"
                    + "                                            <tr>\n"
                    + "                                                <td\n"
                    + "                                                    style=\"font-family: 'Raleway', sans-serif; font-size:20px; color:#2a3a4b;\">\n"
                    + "                                                    <span><img\n"
                    + "                                                            src=\"https://ci6.googleusercontent.com/proxy/qpkhQlR635Jxtsrpbga7PYZv2NCz1935N6GJKvi4CK1fxYt7xAa4Yu9lDzjgJz6236kxpfXX0P-uyKCcHobCgHegZzVMjgQd3WfSiad2pcH1h58=s0-d-e1-ft#https://img.alicdn.com/tfs/TB1Y5JLyhn1gK0jSZKPXXXvUXXa-30-30.png\"\n"
                    + "                                                            alt=\"icon\"\n"
                    + "                                                            style=\"height: 29px; vertical-align:middle\"></span>\n"
                    + "                                                    <div style=\"display: inline-block; margin-left: 7px;\">Order summary\n"
                    + "                                                    </div>\n"
                    + "                                                </td>\n"
                    + "                                            </tr>\n"
                    + "                                            <tr>\n"
                    + "                                                <td height=\"15\"></td>\n"
                    + "                                            </tr>\n"
                    + "                                            <tr>\n"
                    + "                                                <td>\n"
                    + "                                                    <table cellpadding=\"2\" cellspacing=\"0\" width=\"100%\">\n"
                    + "                                                        <tbody>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Payment Method:</td>\n"
                    + "                                                                <td width=\"75%\" style=\"text-align: end;\">Cash On\n"
                    + "                                                                    Delivery</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Quantity:</td>\n"
                    + "                                                                <td width=\"75%\" style=\"text-align: end;\"> " + quantity + "</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Subtotal:</td>\n"
                    + "                                                                <td width=\"75%\" style=\"text-align: end;\"> " + subtotal + "₫</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Shipping fee:</td>\n"
                    + "                                                                <td width=\"75%\" style=\"text-align: end;\"> " + orderDTO.getShippingFee() + "₫</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td colspan=\"2\">\n"
                    + "                                                                    <hr>\n"
                    + "                                                                </td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Total:</td>\n"
                    + "                                                                <td width=\"75%\"\n"
                    + "                                                                    style=\"text-align: end; font-weight:bold; color:#499B14\">\n"
                    + "                                                                    " + total + "₫</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                        </tbody>\n"
                    + "                                                    </table>\n"
                    + "                                                </td>\n"
                    + "                                            </tr>\n"
                    + "                                        </tbody>\n"
                    + "                                    </table>\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"30\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"margin-top: 30px;background:#f0f0f0;height: 9.6px\">\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                        </tbody>\n"
                    + "                    </table>\n"
                    + "                    <!-- END ORDER DETAILS -->\n"
                    + "                </td>\n"
                    + "                <td bgcolor=\"#B3B3B3\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#D1D1D1\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#E8E8E8\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#F8F8F8\" width=\"1px\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#B3B3B3\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#D1D1D1\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#E8E8E8\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#F8F8F8\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "        </tbody>\n"
                    + "    </table>\n"
                    + "</div>",
                    "UTF-8", "html");
            //Gửi email
            Transport.send(msg);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean sendEmailOrderIsShippedToCustomer(OrderDTO orderDTO, UserDTO userDTO) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMPT HOST
        props.put("mail.smtp.port", "587"); //TLS 587 SSL465
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        //calculate total and subtotal
        double subtotal = 0;
        double total = 0;
        int quantity = 0;
        for (ProductDTO product : orderDTO.getProductsList()) {
            subtotal += product.getPrice() * product.getQuantity();
            quantity += product.getQuantity();
        }
        total = subtotal + orderDTO.getShippingFee();
        // Create authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM, PASSWORD);
            }
        };
        Session session = Session.getInstance(props, auth);
        MimeMessage msg = new MimeMessage(session);
        try {
            //Kiểu nội dung
            msg.addHeader("Content-type", "text/html; charset=UTF-8");
            //Ng gửi
            msg.setFrom(FROM);

            //Ng nhận
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userDTO.getEmail(), false));

            //Tiêu đề
            msg.setSubject("Your parcel is being delivered");

            //Quy định email nhận phản hồi
            //msg.setReplyTo()
            //Nội dung
            msg.setText("<div align=\"center\">\n"
                    + "    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:730px\">\n"
                    + "        <tbody>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#E8E8E8\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td bgcolor=\"#F8F8F8\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#E8E8E8\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#D1D1D1\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#FFF\">\n"
                    + "                    <table lang=\"header\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\" style=\"width:100%\">\n"
                    + "                        <tbody>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"70\" valign=\"middle\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"background:#ffffff;height:70\">\n"
                    + "                                    <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" height=\"70\" border=\"0\">\n"
                    + "                                        <tbody>\n"
                    + "                                          <td width=\"100px\"></td>"
                    + "                                            <td align=\"\"\n"
                    + "                                                style=\"font-family: 'Raleway', sans-serif; font-size:34px; color:#499B14; line-height:24px; font-weight: bold; letter-spacing: 1px;  padding-top: 30px;\">\n"
                    + "                                                <span><img\n"
                    + "                                                        src=\"https://lh3.googleusercontent.com/pw/AIL4fc-er2uf7hkMZyYkuvSFfPF7QX9cLbjBt1ACdCa4gc4P0yUicrt-4JvvRI9Rl07p4hbmUDiz6qjMLfINSxuRFJJ503WBWB4NCHgTcrCEN3fuSs4FvD-ueJUqI1agFyQF_1Qrsrh3knuzwbxpC7xSCuGzEpwolK9EkyQH2fJBmWxG_4MGqqb_dLvAIxaOvRnoyeybKKojoSa8PZRv3wPQFNhz0p2JZZpnHzbYyalIzzyClgrL4HX59ZBGEMKKTx4M-4xcPtXhBD-iBDen_xyzhqC_Ba_lXx2OJ3IYyGxeYgIkmBffxv6eJ01dwF54YoULwyajlagTRSQX85Ecv6KM0PQJXaMzhJoGvdA9CISDTxQcV6QTeePNNYH3qedqpKby5zwbc3jqU6J-9DTpeemo3PHw73KYkRH0GLnktP4cmQottwxw8HMwiwG7wBFNb9fbdgDvi1TCf5d1V-XQDY1pbY7eQUb2zqGcVuIi6U00SONfqTrnBtHWIojoDDmyNvJ5PIzLJuNHKD5uz84FHHjEfqcVf1TZV7RczBKwga5h2qat2zWHjy_bl3n3CqIWCOEwUoGRBk2RRXOLzk3KnNKKiv2kRHkXj8N-Ststw0aJZBIhpaUtU8BFjC1-MMsB72b-xYPiYCZ1DImSjT7DDU9DynyhUyoiv1JWKBAORtKV2WWZJk4f87ukwUwSNWUImJjwWy7-fkUEsNpDqxjC8HVNlCCtLce1w7wqLONq24OhMT4TwsGGNkT7Ell8p4zeVYSEOmdRyhiEjmQRZSPTB0b_-Sj2TPCBFXL8FwbiVmQFaixa2hB62bdNamsI0ZEuoRID6dd-QpyNv0tJU9e9UaN2Te199q5qx565y0xrJ5Xgwl6Mt4Y9BteFmgSsgyaHsmvatKJObzuQ2Mqu4RBiiQZgnXA3dLG6LJTivalH6kaGTQVc5a9B7I8-Io__SZk=s250-k-rw-no\"\n"
                    + "                                                        alt=\"icon\"\n"
                    + "                                                        style=\"width:100%;max-width:79px;vertical-align:middle\"></span>\n"
                    + "                                                Bird Meal Order Shop\n"
                    + "                                            </td>\n"
                    + "                                        </tbody>\n"
                    + "                                    </table>\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td height=\"18\"></td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td align=\"center\"\n"
                    + "                                    style=\"font-family: 'Raleway', sans-serif; font-size:22px; font-weight: bold; color:#2a3a4b;\">\n"
                    + "                                    The parcel is being delivered</td>\n"
                    + "                            </tr>\n"
                    + "                        </tbody>\n"
                    + "                    </table>\n"
                    + "                    <table lang=\"header\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\" style=\"width:100%\">\n"
                    + "                        <tbody>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"70\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"padding:20px 30px 30px 30px;background:#ffffff;height:70px\">\n"
                    + "                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" height=\"70\" border=\"0\"\n"
                    + "                                        style=\"width:100%;height:70px\">\n"
                    + "                                        <tbody>\n"
                    + "                                            <tr>\n"
                    + "                                                <td>\n"
                    + "                                                    <h3>Hello " + userDTO.getFullName() + ",</h3>\n"
                    + "                                                    <p>Your order #" + orderDTO.getOrderID() + " has been handed over to the shipping unit. You could receive it in a few days. Please prepare " + total + "VND to pay.</p>\n"
                    + "                                                </td>\n"
                    + "                                        </tbody>\n"
                    + "                                    </table>\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"30\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"margin-top: 30px;background:#f0f0f0;height: 9.6px\">\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                        </tbody>\n"
                    + "                    </table>\n"
                    + "                    <!-- START DELIVERY DETAILS -->\n"
                    + "                    <table lang=\"header\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\" style=\"width:100%\">\n"
                    + "                        <tbody>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"70\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"padding:30px;background:#ffffff;height:70px\">\n"
                    + "                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" height=\"70\" border=\"0\"\n"
                    + "                                        style=\"width:100%;height:70px\">\n"
                    + "                                        <tbody>\n"
                    + "                                            <tr>\n"
                    + "                                                <td\n"
                    + "                                                    style=\"font-family: 'Raleway', sans-serif; font-size:20px; color:#2a3a4b;\">\n"
                    + "                                                    <span><img\n"
                    + "                                                            src=\"https://ci6.googleusercontent.com/proxy/AUmakQI121QQ_WvdspszI-42jmht-u29A4iIvgfX5V-q3Z9qyUAfpUFNiqpGKP4CW6L1x8cnoxHpjGrhiuLmDr8J92EgI9gpuNCZdjW70wzcGuk=s0-d-e1-ft#https://img.alicdn.com/tfs/TB1ciNPybj1gK0jSZFOXXc7GpXa-48-48.png\"\n"
                    + "                                                            alt=\"icon\"\n"
                    + "                                                            style=\"height: 29px; vertical-align:middle\"></span>\n"
                    + "                                                    <div style=\"display: inline-block; margin-left: 7px;\">Delivery\n"
                    + "                                                        details</div>\n"
                    + "                                                </td>\n"
                    + "                                            </tr>\n"
                    + "                                            <tr>\n"
                    + "                                                <td height=\"15\"></td>\n"
                    + "                                            </tr>\n"
                    + "                                            <tr>\n"
                    + "                                                <td>\n"
                    + "                                                    <table cellpadding=\"2\" cellspacing=\"0\" width=\"100%\">\n"
                    + "                                                        <tbody>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" valign=\"top\" style=\"font-weight:bold\">\n"
                    + "                                                                    Name:</td>\n"
                    + "                                                                <td width=\"75%\" valign=\"top\">" + orderDTO.getFullName() + "</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td style=\"font-weight:bold\">Address:</td>\n"
                    + "                                                                <td>" + orderDTO.getOrderAddress() + "</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td valign=\"top\" style=\"font-weight:bold\">Phone:\n"
                    + "                                                                </td>\n"
                    + "                                                                <td valign=\"top\">" + orderDTO.getPhoneNumber() + "</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                        </tbody>\n"
                    + "                                                    </table>\n"
                    + "                                                </td>\n"
                    + "                                            </tr>\n"
                    + "                                        </tbody>\n"
                    + "                                    </table>\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"30\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"margin-top: 30px;background:#f0f0f0;height: 9.6px\">\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                        </tbody>\n"
                    + "                    </table>\n"
                    + "                    <!-- END DELIVERY DETAILS -->\n"
                    + "                    <!-- START ORDER DETAIL -->\n"
                    + "                    <table lang=\"header\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\" style=\"width:100%\">\n"
                    + "                        <tbody>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"70\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"padding:30px;background:#ffffff;height:70px\">\n"
                    + "                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" height=\"70\" border=\"0\"\n"
                    + "                                        style=\"width:100%;height:70px\">\n"
                    + "                                        <tbody>\n"
                    + "                                            <tr>\n"
                    + "                                                <td\n"
                    + "                                                    style=\"font-family: 'Raleway', sans-serif; font-size:20px; color:#2a3a4b;\">\n"
                    + "                                                    <span><img\n"
                    + "                                                            src=\"https://ci6.googleusercontent.com/proxy/qpkhQlR635Jxtsrpbga7PYZv2NCz1935N6GJKvi4CK1fxYt7xAa4Yu9lDzjgJz6236kxpfXX0P-uyKCcHobCgHegZzVMjgQd3WfSiad2pcH1h58=s0-d-e1-ft#https://img.alicdn.com/tfs/TB1Y5JLyhn1gK0jSZKPXXXvUXXa-30-30.png\"\n"
                    + "                                                            alt=\"icon\"\n"
                    + "                                                            style=\"height: 29px; vertical-align:middle\"></span>\n"
                    + "                                                    <div style=\"display: inline-block; margin-left: 7px;\">Order summary\n"
                    + "                                                    </div>\n"
                    + "                                                </td>\n"
                    + "                                            </tr>\n"
                    + "                                            <tr>\n"
                    + "                                                <td height=\"15\"></td>\n"
                    + "                                            </tr>\n"
                    + "                                            <tr>\n"
                    + "                                                <td>\n"
                    + "                                                    <table cellpadding=\"2\" cellspacing=\"0\" width=\"100%\">\n"
                    + "                                                        <tbody>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Payment Method:</td>\n"
                    + "                                                                <td width=\"75%\" style=\"text-align: end;\">Cash On\n"
                    + "                                                                    Delivery</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Quantity:</td>\n"
                    + "                                                                <td width=\"75%\" style=\"text-align: end;\"> " + quantity + "</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Subtotal:</td>\n"
                    + "                                                                <td width=\"75%\" style=\"text-align: end;\"> " + subtotal + "₫</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Shipping fee:</td>\n"
                    + "                                                                <td width=\"75%\" style=\"text-align: end;\"> " + orderDTO.getShippingFee() + "₫</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td colspan=\"2\">\n"
                    + "                                                                    <hr>\n"
                    + "                                                                </td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Total:</td>\n"
                    + "                                                                <td width=\"75%\"\n"
                    + "                                                                    style=\"text-align: end; font-weight:bold; color:#499B14\">\n"
                    + "                                                                    " + total + "₫</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                        </tbody>\n"
                    + "                                                    </table>\n"
                    + "                                                </td>\n"
                    + "                                            </tr>\n"
                    + "                                        </tbody>\n"
                    + "                                    </table>\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"30\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"margin-top: 30px;background:#f0f0f0;height: 9.6px\">\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                        </tbody>\n"
                    + "                    </table>\n"
                    + "                    <!-- END ORDER DETAILS -->\n"
                    + "                </td>\n"
                    + "                <td bgcolor=\"#B3B3B3\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#D1D1D1\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#E8E8E8\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#F8F8F8\" width=\"1px\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#B3B3B3\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#D1D1D1\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#E8E8E8\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#F8F8F8\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "        </tbody>\n"
                    + "    </table>\n"
                    + "</div>",
                    "UTF-8", "html");
            //Gửi email
            Transport.send(msg);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean sendEmailOrderIsShippedNotSuccessfullyToCustomer(OrderDTO orderDTO, UserDTO userDTO) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMPT HOST
        props.put("mail.smtp.port", "587"); //TLS 587 SSL465
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        //calculate total and subtotal
        double subtotal = 0;
        double total = 0;
        int quantity = 0;
        for (ProductDTO product : orderDTO.getProductsList()) {
            subtotal += product.getPrice() * product.getQuantity();
            quantity += product.getQuantity();
        }
        total = subtotal + orderDTO.getShippingFee();
        // Create authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM, PASSWORD);
            }
        };
        Session session = Session.getInstance(props, auth);
        MimeMessage msg = new MimeMessage(session);
        try {
            //Kiểu nội dung
            msg.addHeader("Content-type", "text/html; charset=UTF-8");
            //Ng gửi
            msg.setFrom(FROM);

            //Ng nhận
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userDTO.getEmail(), false));

            //Tiêu đề
            msg.setSubject("Your parcel was not delivered successfully");

            //Quy định email nhận phản hồi
            //msg.setReplyTo()
            //Nội dung
            msg.setText("<div align=\"center\">\n"
                    + "    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:730px\">\n"
                    + "        <tbody>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#E8E8E8\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td bgcolor=\"#F8F8F8\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#E8E8E8\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#D1D1D1\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#FFF\">\n"
                    + "                    <table lang=\"header\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\" style=\"width:100%\">\n"
                    + "                        <tbody>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"70\" valign=\"middle\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"background:#ffffff;height:70\">\n"
                    + "                                    <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" height=\"70\" border=\"0\">\n"
                    + "                                        <tbody>\n"
                    + "                                          <td width=\"100px\"></td>"
                    + "                                            <td align=\"\"\n"
                    + "                                                style=\"font-family: 'Raleway', sans-serif; font-size:34px; color:#499B14; line-height:24px; font-weight: bold; letter-spacing: 1px;  padding-top: 30px;\">\n"
                    + "                                                <span><img\n"
                    + "                                                        src=\"https://lh3.googleusercontent.com/pw/AIL4fc-er2uf7hkMZyYkuvSFfPF7QX9cLbjBt1ACdCa4gc4P0yUicrt-4JvvRI9Rl07p4hbmUDiz6qjMLfINSxuRFJJ503WBWB4NCHgTcrCEN3fuSs4FvD-ueJUqI1agFyQF_1Qrsrh3knuzwbxpC7xSCuGzEpwolK9EkyQH2fJBmWxG_4MGqqb_dLvAIxaOvRnoyeybKKojoSa8PZRv3wPQFNhz0p2JZZpnHzbYyalIzzyClgrL4HX59ZBGEMKKTx4M-4xcPtXhBD-iBDen_xyzhqC_Ba_lXx2OJ3IYyGxeYgIkmBffxv6eJ01dwF54YoULwyajlagTRSQX85Ecv6KM0PQJXaMzhJoGvdA9CISDTxQcV6QTeePNNYH3qedqpKby5zwbc3jqU6J-9DTpeemo3PHw73KYkRH0GLnktP4cmQottwxw8HMwiwG7wBFNb9fbdgDvi1TCf5d1V-XQDY1pbY7eQUb2zqGcVuIi6U00SONfqTrnBtHWIojoDDmyNvJ5PIzLJuNHKD5uz84FHHjEfqcVf1TZV7RczBKwga5h2qat2zWHjy_bl3n3CqIWCOEwUoGRBk2RRXOLzk3KnNKKiv2kRHkXj8N-Ststw0aJZBIhpaUtU8BFjC1-MMsB72b-xYPiYCZ1DImSjT7DDU9DynyhUyoiv1JWKBAORtKV2WWZJk4f87ukwUwSNWUImJjwWy7-fkUEsNpDqxjC8HVNlCCtLce1w7wqLONq24OhMT4TwsGGNkT7Ell8p4zeVYSEOmdRyhiEjmQRZSPTB0b_-Sj2TPCBFXL8FwbiVmQFaixa2hB62bdNamsI0ZEuoRID6dd-QpyNv0tJU9e9UaN2Te199q5qx565y0xrJ5Xgwl6Mt4Y9BteFmgSsgyaHsmvatKJObzuQ2Mqu4RBiiQZgnXA3dLG6LJTivalH6kaGTQVc5a9B7I8-Io__SZk=s250-k-rw-no\"\n"
                    + "                                                        alt=\"icon\"\n"
                    + "                                                        style=\"width:100%;max-width:79px;vertical-align:middle\"></span>\n"
                    + "                                                Bird Meal Order Shop\n"
                    + "                                            </td>\n"
                    + "                                        </tbody>\n"
                    + "                                    </table>\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td height=\"18\"></td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td align=\"center\"\n"
                    + "                                    style=\"font-family: 'Raleway', sans-serif; font-size:22px; font-weight: bold; color:#2a3a4b;\">\n"
                    + "                                    The parcel will return to our shop.</td>\n"
                    + "                            </tr>\n"
                    + "                        </tbody>\n"
                    + "                    </table>\n"
                    + "                    <table lang=\"header\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\" style=\"width:100%\">\n"
                    + "                        <tbody>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"70\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"padding:20px 30px 30px 30px;background:#ffffff;height:70px\">\n"
                    + "                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" height=\"70\" border=\"0\"\n"
                    + "                                        style=\"width:100%;height:70px\">\n"
                    + "                                        <tbody>\n"
                    + "                                            <tr>\n"
                    + "                                                <td>\n"
                    + "                                                    <h3>Hello " + userDTO.getFullName() + ",</h3>\n"
                    + "                                                    <p>We regret that you did not receive your order #" + orderDTO.getOrderID() + ". It will return to our shop.</p>\n"
                    + "                                                </td>\n"
                    + "                                        </tbody>\n"
                    + "                                    </table>\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"30\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"margin-top: 30px;background:#f0f0f0;height: 9.6px\">\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                        </tbody>\n"
                    + "                    </table>\n"
                    + "                    <!-- START DELIVERY DETAILS -->\n"
                    + "                    <table lang=\"header\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\" style=\"width:100%\">\n"
                    + "                        <tbody>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"70\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"padding:30px;background:#ffffff;height:70px\">\n"
                    + "                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" height=\"70\" border=\"0\"\n"
                    + "                                        style=\"width:100%;height:70px\">\n"
                    + "                                        <tbody>\n"
                    + "                                            <tr>\n"
                    + "                                                <td\n"
                    + "                                                    style=\"font-family: 'Raleway', sans-serif; font-size:20px; color:#2a3a4b;\">\n"
                    + "                                                    <span><img\n"
                    + "                                                            src=\"https://ci6.googleusercontent.com/proxy/AUmakQI121QQ_WvdspszI-42jmht-u29A4iIvgfX5V-q3Z9qyUAfpUFNiqpGKP4CW6L1x8cnoxHpjGrhiuLmDr8J92EgI9gpuNCZdjW70wzcGuk=s0-d-e1-ft#https://img.alicdn.com/tfs/TB1ciNPybj1gK0jSZFOXXc7GpXa-48-48.png\"\n"
                    + "                                                            alt=\"icon\"\n"
                    + "                                                            style=\"height: 29px; vertical-align:middle\"></span>\n"
                    + "                                                    <div style=\"display: inline-block; margin-left: 7px;\">Delivery\n"
                    + "                                                        details</div>\n"
                    + "                                                </td>\n"
                    + "                                            </tr>\n"
                    + "                                            <tr>\n"
                    + "                                                <td height=\"15\"></td>\n"
                    + "                                            </tr>\n"
                    + "                                            <tr>\n"
                    + "                                                <td>\n"
                    + "                                                    <table cellpadding=\"2\" cellspacing=\"0\" width=\"100%\">\n"
                    + "                                                        <tbody>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" valign=\"top\" style=\"font-weight:bold\">\n"
                    + "                                                                    Name:</td>\n"
                    + "                                                                <td width=\"75%\" valign=\"top\">" + orderDTO.getFullName() + "</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td style=\"font-weight:bold\">Address:</td>\n"
                    + "                                                                <td>" + orderDTO.getOrderAddress() + "</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td valign=\"top\" style=\"font-weight:bold\">Phone:\n"
                    + "                                                                </td>\n"
                    + "                                                                <td valign=\"top\">" + orderDTO.getPhoneNumber() + "</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                        </tbody>\n"
                    + "                                                    </table>\n"
                    + "                                                </td>\n"
                    + "                                            </tr>\n"
                    + "                                        </tbody>\n"
                    + "                                    </table>\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"30\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"margin-top: 30px;background:#f0f0f0;height: 9.6px\">\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                        </tbody>\n"
                    + "                    </table>\n"
                    + "                    <!-- END DELIVERY DETAILS -->\n"
                    + "                    <!-- START ORDER DETAIL -->\n"
                    + "                    <table lang=\"header\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\" style=\"width:100%\">\n"
                    + "                        <tbody>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"70\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"padding:30px;background:#ffffff;height:70px\">\n"
                    + "                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" height=\"70\" border=\"0\"\n"
                    + "                                        style=\"width:100%;height:70px\">\n"
                    + "                                        <tbody>\n"
                    + "                                            <tr>\n"
                    + "                                                <td\n"
                    + "                                                    style=\"font-family: 'Raleway', sans-serif; font-size:20px; color:#2a3a4b;\">\n"
                    + "                                                    <span><img\n"
                    + "                                                            src=\"https://ci6.googleusercontent.com/proxy/qpkhQlR635Jxtsrpbga7PYZv2NCz1935N6GJKvi4CK1fxYt7xAa4Yu9lDzjgJz6236kxpfXX0P-uyKCcHobCgHegZzVMjgQd3WfSiad2pcH1h58=s0-d-e1-ft#https://img.alicdn.com/tfs/TB1Y5JLyhn1gK0jSZKPXXXvUXXa-30-30.png\"\n"
                    + "                                                            alt=\"icon\"\n"
                    + "                                                            style=\"height: 29px; vertical-align:middle\"></span>\n"
                    + "                                                    <div style=\"display: inline-block; margin-left: 7px;\">Order summary\n"
                    + "                                                    </div>\n"
                    + "                                                </td>\n"
                    + "                                            </tr>\n"
                    + "                                            <tr>\n"
                    + "                                                <td height=\"15\"></td>\n"
                    + "                                            </tr>\n"
                    + "                                            <tr>\n"
                    + "                                                <td>\n"
                    + "                                                    <table cellpadding=\"2\" cellspacing=\"0\" width=\"100%\">\n"
                    + "                                                        <tbody>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Payment Method:</td>\n"
                    + "                                                                <td width=\"75%\" style=\"text-align: end;\">Cash On\n"
                    + "                                                                    Delivery</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Quantity:</td>\n"
                    + "                                                                <td width=\"75%\" style=\"text-align: end;\"> " + quantity + "</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Subtotal:</td>\n"
                    + "                                                                <td width=\"75%\" style=\"text-align: end;\"> " + subtotal + "₫</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Shipping fee:</td>\n"
                    + "                                                                <td width=\"75%\" style=\"text-align: end;\"> " + orderDTO.getShippingFee() + "₫</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td colspan=\"2\">\n"
                    + "                                                                    <hr>\n"
                    + "                                                                </td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Total:</td>\n"
                    + "                                                                <td width=\"75%\"\n"
                    + "                                                                    style=\"text-align: end; font-weight:bold; color:#499B14\">\n"
                    + "                                                                    " + total + "₫</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                        </tbody>\n"
                    + "                                                    </table>\n"
                    + "                                                </td>\n"
                    + "                                            </tr>\n"
                    + "                                        </tbody>\n"
                    + "                                    </table>\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"30\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"margin-top: 30px;background:#f0f0f0;height: 9.6px\">\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                        </tbody>\n"
                    + "                    </table>\n"
                    + "                    <!-- END ORDER DETAILS -->\n"
                    + "                </td>\n"
                    + "                <td bgcolor=\"#B3B3B3\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#D1D1D1\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#E8E8E8\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#F8F8F8\" width=\"1px\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#B3B3B3\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#D1D1D1\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#E8E8E8\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#F8F8F8\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "        </tbody>\n"
                    + "    </table>\n"
                    + "</div>",
                    "UTF-8", "html");
            //Gửi email
            Transport.send(msg);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean sendEmailOrderIsShippedSuccessfullyToCustomer(OrderDTO orderDTO, UserDTO userDTO) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMPT HOST
        props.put("mail.smtp.port", "587"); //TLS 587 SSL465
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        //calculate total and subtotal
        double subtotal = 0;
        double total = 0;
        int quantity = 0;
        for (ProductDTO product : orderDTO.getProductsList()) {
            subtotal += product.getPrice() * product.getQuantity();
            quantity += product.getQuantity();
        }
        total = subtotal + orderDTO.getShippingFee();
        // Create authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM, PASSWORD);
            }
        };
        Session session = Session.getInstance(props, auth);
        MimeMessage msg = new MimeMessage(session);
        try {
            //Kiểu nội dung
            msg.addHeader("Content-type", "text/html; charset=UTF-8");
            //Ng gửi
            msg.setFrom(FROM);

            //Ng nhận
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userDTO.getEmail(), false));

            //Tiêu đề
            msg.setSubject("Your parcel was delivered successfully");

            //Quy định email nhận phản hồi
            //msg.setReplyTo()
            //Nội dung
            msg.setText("<div align=\"center\">\n"
                    + "    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:730px\">\n"
                    + "        <tbody>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#E8E8E8\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td bgcolor=\"#F8F8F8\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#E8E8E8\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#D1D1D1\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#FFF\">\n"
                    + "                    <table lang=\"header\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\" style=\"width:100%\">\n"
                    + "                        <tbody>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"70\" valign=\"middle\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"background:#ffffff;height:70\">\n"
                    + "                                    <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" height=\"70\" border=\"0\">\n"
                    + "                                        <tbody>\n"
                    + "                                          <td width=\"100px\"></td>"
                    + "                                            <td align=\"\"\n"
                    + "                                                style=\"font-family: 'Raleway', sans-serif; font-size:34px; color:#499B14; line-height:24px; font-weight: bold; letter-spacing: 1px;  padding-top: 30px;\">\n"
                    + "                                                <span><img\n"
                    + "                                                        src=\"https://lh3.googleusercontent.com/pw/AIL4fc-er2uf7hkMZyYkuvSFfPF7QX9cLbjBt1ACdCa4gc4P0yUicrt-4JvvRI9Rl07p4hbmUDiz6qjMLfINSxuRFJJ503WBWB4NCHgTcrCEN3fuSs4FvD-ueJUqI1agFyQF_1Qrsrh3knuzwbxpC7xSCuGzEpwolK9EkyQH2fJBmWxG_4MGqqb_dLvAIxaOvRnoyeybKKojoSa8PZRv3wPQFNhz0p2JZZpnHzbYyalIzzyClgrL4HX59ZBGEMKKTx4M-4xcPtXhBD-iBDen_xyzhqC_Ba_lXx2OJ3IYyGxeYgIkmBffxv6eJ01dwF54YoULwyajlagTRSQX85Ecv6KM0PQJXaMzhJoGvdA9CISDTxQcV6QTeePNNYH3qedqpKby5zwbc3jqU6J-9DTpeemo3PHw73KYkRH0GLnktP4cmQottwxw8HMwiwG7wBFNb9fbdgDvi1TCf5d1V-XQDY1pbY7eQUb2zqGcVuIi6U00SONfqTrnBtHWIojoDDmyNvJ5PIzLJuNHKD5uz84FHHjEfqcVf1TZV7RczBKwga5h2qat2zWHjy_bl3n3CqIWCOEwUoGRBk2RRXOLzk3KnNKKiv2kRHkXj8N-Ststw0aJZBIhpaUtU8BFjC1-MMsB72b-xYPiYCZ1DImSjT7DDU9DynyhUyoiv1JWKBAORtKV2WWZJk4f87ukwUwSNWUImJjwWy7-fkUEsNpDqxjC8HVNlCCtLce1w7wqLONq24OhMT4TwsGGNkT7Ell8p4zeVYSEOmdRyhiEjmQRZSPTB0b_-Sj2TPCBFXL8FwbiVmQFaixa2hB62bdNamsI0ZEuoRID6dd-QpyNv0tJU9e9UaN2Te199q5qx565y0xrJ5Xgwl6Mt4Y9BteFmgSsgyaHsmvatKJObzuQ2Mqu4RBiiQZgnXA3dLG6LJTivalH6kaGTQVc5a9B7I8-Io__SZk=s250-k-rw-no\"\n"
                    + "                                                        alt=\"icon\"\n"
                    + "                                                        style=\"width:100%;max-width:79px;vertical-align:middle\"></span>\n"
                    + "                                                Bird Meal Order Shop\n"
                    + "                                            </td>\n"
                    + "                                        </tbody>\n"
                    + "                                    </table>\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td height=\"18\"></td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td align=\"center\"\n"
                    + "                                    style=\"font-family: 'Raleway', sans-serif; font-size:22px; font-weight: bold; color:#2a3a4b;\">\n"
                    + "                                    Your parcel was delivered successfully.</td>\n"
                    + "                            </tr>\n"
                    + "                        </tbody>\n"
                    + "                    </table>\n"
                    + "                    <table lang=\"header\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\" style=\"width:100%\">\n"
                    + "                        <tbody>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"70\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"padding:20px 30px 30px 30px;background:#ffffff;height:70px\">\n"
                    + "                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" height=\"70\" border=\"0\"\n"
                    + "                                        style=\"width:100%;height:70px\">\n"
                    + "                                        <tbody>\n"
                    + "                                            <tr>\n"
                    + "                                                <td>\n"
                    + "                                                    <h3>Hello " + userDTO.getFullName() + ",</h3>\n"
                    + "                                                    <p>We are grateful that you place the order #" + orderDTO.getOrderID() + " in our shop. Hope to serve you again and receive your feedback on our website. Your feedback will make us improve our services.</p>\n"
                    + "                                                </td>\n"
                    + "                                        </tbody>\n"
                    + "                                    </table>\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"30\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"margin-top: 30px;background:#f0f0f0;height: 9.6px\">\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                        </tbody>\n"
                    + "                    </table>\n"
                    + "                    <!-- START DELIVERY DETAILS -->\n"
                    + "                    <table lang=\"header\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\" style=\"width:100%\">\n"
                    + "                        <tbody>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"70\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"padding:30px;background:#ffffff;height:70px\">\n"
                    + "                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" height=\"70\" border=\"0\"\n"
                    + "                                        style=\"width:100%;height:70px\">\n"
                    + "                                        <tbody>\n"
                    + "                                            <tr>\n"
                    + "                                                <td\n"
                    + "                                                    style=\"font-family: 'Raleway', sans-serif; font-size:20px; color:#2a3a4b;\">\n"
                    + "                                                    <span><img\n"
                    + "                                                            src=\"https://ci6.googleusercontent.com/proxy/AUmakQI121QQ_WvdspszI-42jmht-u29A4iIvgfX5V-q3Z9qyUAfpUFNiqpGKP4CW6L1x8cnoxHpjGrhiuLmDr8J92EgI9gpuNCZdjW70wzcGuk=s0-d-e1-ft#https://img.alicdn.com/tfs/TB1ciNPybj1gK0jSZFOXXc7GpXa-48-48.png\"\n"
                    + "                                                            alt=\"icon\"\n"
                    + "                                                            style=\"height: 29px; vertical-align:middle\"></span>\n"
                    + "                                                    <div style=\"display: inline-block; margin-left: 7px;\">Delivery\n"
                    + "                                                        details</div>\n"
                    + "                                                </td>\n"
                    + "                                            </tr>\n"
                    + "                                            <tr>\n"
                    + "                                                <td height=\"15\"></td>\n"
                    + "                                            </tr>\n"
                    + "                                            <tr>\n"
                    + "                                                <td>\n"
                    + "                                                    <table cellpadding=\"2\" cellspacing=\"0\" width=\"100%\">\n"
                    + "                                                        <tbody>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" valign=\"top\" style=\"font-weight:bold\">\n"
                    + "                                                                    Name:</td>\n"
                    + "                                                                <td width=\"75%\" valign=\"top\">" + orderDTO.getFullName() + "</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td style=\"font-weight:bold\">Address:</td>\n"
                    + "                                                                <td>" + orderDTO.getOrderAddress() + "</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td valign=\"top\" style=\"font-weight:bold\">Phone:\n"
                    + "                                                                </td>\n"
                    + "                                                                <td valign=\"top\">" + orderDTO.getPhoneNumber() + "</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                        </tbody>\n"
                    + "                                                    </table>\n"
                    + "                                                </td>\n"
                    + "                                            </tr>\n"
                    + "                                        </tbody>\n"
                    + "                                    </table>\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"30\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"margin-top: 30px;background:#f0f0f0;height: 9.6px\">\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                        </tbody>\n"
                    + "                    </table>\n"
                    + "                    <!-- END DELIVERY DETAILS -->\n"
                    + "                    <!-- START ORDER DETAIL -->\n"
                    + "                    <table lang=\"header\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\" style=\"width:100%\">\n"
                    + "                        <tbody>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"70\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"padding:30px;background:#ffffff;height:70px\">\n"
                    + "                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" height=\"70\" border=\"0\"\n"
                    + "                                        style=\"width:100%;height:70px\">\n"
                    + "                                        <tbody>\n"
                    + "                                            <tr>\n"
                    + "                                                <td\n"
                    + "                                                    style=\"font-family: 'Raleway', sans-serif; font-size:20px; color:#2a3a4b;\">\n"
                    + "                                                    <span><img\n"
                    + "                                                            src=\"https://ci6.googleusercontent.com/proxy/qpkhQlR635Jxtsrpbga7PYZv2NCz1935N6GJKvi4CK1fxYt7xAa4Yu9lDzjgJz6236kxpfXX0P-uyKCcHobCgHegZzVMjgQd3WfSiad2pcH1h58=s0-d-e1-ft#https://img.alicdn.com/tfs/TB1Y5JLyhn1gK0jSZKPXXXvUXXa-30-30.png\"\n"
                    + "                                                            alt=\"icon\"\n"
                    + "                                                            style=\"height: 29px; vertical-align:middle\"></span>\n"
                    + "                                                    <div style=\"display: inline-block; margin-left: 7px;\">Order summary\n"
                    + "                                                    </div>\n"
                    + "                                                </td>\n"
                    + "                                            </tr>\n"
                    + "                                            <tr>\n"
                    + "                                                <td height=\"15\"></td>\n"
                    + "                                            </tr>\n"
                    + "                                            <tr>\n"
                    + "                                                <td>\n"
                    + "                                                    <table cellpadding=\"2\" cellspacing=\"0\" width=\"100%\">\n"
                    + "                                                        <tbody>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Payment Method:</td>\n"
                    + "                                                                <td width=\"75%\" style=\"text-align: end;\">Cash On\n"
                    + "                                                                    Delivery</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Quantity:</td>\n"
                    + "                                                                <td width=\"75%\" style=\"text-align: end;\"> " + quantity + "</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Subtotal:</td>\n"
                    + "                                                                <td width=\"75%\" style=\"text-align: end;\"> " + subtotal + "₫</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Shipping fee:</td>\n"
                    + "                                                                <td width=\"75%\" style=\"text-align: end;\"> " + orderDTO.getShippingFee() + "₫</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td colspan=\"2\">\n"
                    + "                                                                    <hr>\n"
                    + "                                                                </td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Total:</td>\n"
                    + "                                                                <td width=\"75%\"\n"
                    + "                                                                    style=\"text-align: end; font-weight:bold; color:#499B14\">\n"
                    + "                                                                    " + total + "₫</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                        </tbody>\n"
                    + "                                                    </table>\n"
                    + "                                                </td>\n"
                    + "                                            </tr>\n"
                    + "                                        </tbody>\n"
                    + "                                    </table>\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"30\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"margin-top: 30px;background:#f0f0f0;height: 9.6px\">\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                        </tbody>\n"
                    + "                    </table>\n"
                    + "                    <!-- END ORDER DETAILS -->\n"
                    + "                </td>\n"
                    + "                <td bgcolor=\"#B3B3B3\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#D1D1D1\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#E8E8E8\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#F8F8F8\" width=\"1px\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#B3B3B3\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#D1D1D1\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#E8E8E8\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#F8F8F8\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "        </tbody>\n"
                    + "    </table>\n"
                    + "</div>",
                    "UTF-8", "html");
            //Gửi email
            Transport.send(msg);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean sendEmailOrderIsCancelledToCustomer(OrderDTO orderDTO, UserDTO userDTO) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMPT HOST
        props.put("mail.smtp.port", "587"); //TLS 587 SSL465
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        //calculate total and subtotal
        double subtotal = 0;
        double total = 0;
        int quantity = 0;
        for (ProductDTO product : orderDTO.getProductsList()) {
            subtotal += product.getPrice() * product.getQuantity();
            quantity += product.getQuantity();
        }
        total = subtotal + orderDTO.getShippingFee();
        // Create authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM, PASSWORD);
            }
        };
        Session session = Session.getInstance(props, auth);
        MimeMessage msg = new MimeMessage(session);
        try {
            //Kiểu nội dung
            msg.addHeader("Content-type", "text/html; charset=UTF-8");
            //Ng gửi
            msg.setFrom(FROM);

            //Ng nhận
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userDTO.getEmail(), false));

            //Tiêu đề
            msg.setSubject("Your parcel was cancelled");

            //Quy định email nhận phản hồi
            //msg.setReplyTo()
            //Nội dung
            msg.setText("<div align=\"center\">\n"
                    + "    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:730px\">\n"
                    + "        <tbody>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#E8E8E8\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td bgcolor=\"#F8F8F8\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#E8E8E8\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#D1D1D1\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#FFF\">\n"
                    + "                    <table lang=\"header\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\" style=\"width:100%\">\n"
                    + "                        <tbody>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"70\" valign=\"middle\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"background:#ffffff;height:70\">\n"
                    + "                                    <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" height=\"70\" border=\"0\">\n"
                    + "                                        <tbody>\n"
                    + "                                          <td width=\"100px\"></td>"
                    + "                                            <td align=\"\"\n"
                    + "                                                style=\"font-family: 'Raleway', sans-serif; font-size:34px; color:#499B14; line-height:24px; font-weight: bold; letter-spacing: 1px;  padding-top: 30px;\">\n"
                    + "                                                <span><img\n"
                    + "                                                        src=\"https://lh3.googleusercontent.com/pw/AIL4fc-er2uf7hkMZyYkuvSFfPF7QX9cLbjBt1ACdCa4gc4P0yUicrt-4JvvRI9Rl07p4hbmUDiz6qjMLfINSxuRFJJ503WBWB4NCHgTcrCEN3fuSs4FvD-ueJUqI1agFyQF_1Qrsrh3knuzwbxpC7xSCuGzEpwolK9EkyQH2fJBmWxG_4MGqqb_dLvAIxaOvRnoyeybKKojoSa8PZRv3wPQFNhz0p2JZZpnHzbYyalIzzyClgrL4HX59ZBGEMKKTx4M-4xcPtXhBD-iBDen_xyzhqC_Ba_lXx2OJ3IYyGxeYgIkmBffxv6eJ01dwF54YoULwyajlagTRSQX85Ecv6KM0PQJXaMzhJoGvdA9CISDTxQcV6QTeePNNYH3qedqpKby5zwbc3jqU6J-9DTpeemo3PHw73KYkRH0GLnktP4cmQottwxw8HMwiwG7wBFNb9fbdgDvi1TCf5d1V-XQDY1pbY7eQUb2zqGcVuIi6U00SONfqTrnBtHWIojoDDmyNvJ5PIzLJuNHKD5uz84FHHjEfqcVf1TZV7RczBKwga5h2qat2zWHjy_bl3n3CqIWCOEwUoGRBk2RRXOLzk3KnNKKiv2kRHkXj8N-Ststw0aJZBIhpaUtU8BFjC1-MMsB72b-xYPiYCZ1DImSjT7DDU9DynyhUyoiv1JWKBAORtKV2WWZJk4f87ukwUwSNWUImJjwWy7-fkUEsNpDqxjC8HVNlCCtLce1w7wqLONq24OhMT4TwsGGNkT7Ell8p4zeVYSEOmdRyhiEjmQRZSPTB0b_-Sj2TPCBFXL8FwbiVmQFaixa2hB62bdNamsI0ZEuoRID6dd-QpyNv0tJU9e9UaN2Te199q5qx565y0xrJ5Xgwl6Mt4Y9BteFmgSsgyaHsmvatKJObzuQ2Mqu4RBiiQZgnXA3dLG6LJTivalH6kaGTQVc5a9B7I8-Io__SZk=s250-k-rw-no\"\n"
                    + "                                                        alt=\"icon\"\n"
                    + "                                                        style=\"width:100%;max-width:79px;vertical-align:middle\"></span>\n"
                    + "                                                Bird Meal Order Shop\n"
                    + "                                            </td>\n"
                    + "                                        </tbody>\n"
                    + "                                    </table>\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td height=\"18\"></td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td align=\"center\"\n"
                    + "                                    style=\"font-family: 'Raleway', sans-serif; font-size:22px; font-weight: bold; color:#2a3a4b;\">\n"
                    + "                                    Your parcel has been cancelled.</td>\n"
                    + "                            </tr>\n"
                    + "                        </tbody>\n"
                    + "                    </table>\n"
                    + "                    <table lang=\"header\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\" style=\"width:100%\">\n"
                    + "                        <tbody>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"70\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"padding:20px 30px 30px 30px;background:#ffffff;height:70px\">\n"
                    + "                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" height=\"70\" border=\"0\"\n"
                    + "                                        style=\"width:100%;height:70px\">\n"
                    + "                                        <tbody>\n"
                    + "                                            <tr>\n"
                    + "                                                <td>\n"
                    + "                                                    <h3>Hello " + userDTO.getFullName() + ",</h3>\n"
                    + "                                                    <p>We are regret that your order #" + orderDTO.getOrderID() + " has been cancelled.</p>\n"
                    + "                                                </td>\n"
                    + "                                        </tbody>\n"
                    + "                                    </table>\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"30\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"margin-top: 30px;background:#f0f0f0;height: 9.6px\">\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                        </tbody>\n"
                    + "                    </table>\n"
                    + "                    <!-- START DELIVERY DETAILS -->\n"
                    + "                    <table lang=\"header\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\" style=\"width:100%\">\n"
                    + "                        <tbody>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"70\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"padding:30px;background:#ffffff;height:70px\">\n"
                    + "                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" height=\"70\" border=\"0\"\n"
                    + "                                        style=\"width:100%;height:70px\">\n"
                    + "                                        <tbody>\n"
                    + "                                            <tr>\n"
                    + "                                                <td\n"
                    + "                                                    style=\"font-family: 'Raleway', sans-serif; font-size:20px; color:#2a3a4b;\">\n"
                    + "                                                    <span><img\n"
                    + "                                                            src=\"https://ci6.googleusercontent.com/proxy/AUmakQI121QQ_WvdspszI-42jmht-u29A4iIvgfX5V-q3Z9qyUAfpUFNiqpGKP4CW6L1x8cnoxHpjGrhiuLmDr8J92EgI9gpuNCZdjW70wzcGuk=s0-d-e1-ft#https://img.alicdn.com/tfs/TB1ciNPybj1gK0jSZFOXXc7GpXa-48-48.png\"\n"
                    + "                                                            alt=\"icon\"\n"
                    + "                                                            style=\"height: 29px; vertical-align:middle\"></span>\n"
                    + "                                                    <div style=\"display: inline-block; margin-left: 7px;\">Delivery\n"
                    + "                                                        details</div>\n"
                    + "                                                </td>\n"
                    + "                                            </tr>\n"
                    + "                                            <tr>\n"
                    + "                                                <td height=\"15\"></td>\n"
                    + "                                            </tr>\n"
                    + "                                            <tr>\n"
                    + "                                                <td>\n"
                    + "                                                    <table cellpadding=\"2\" cellspacing=\"0\" width=\"100%\">\n"
                    + "                                                        <tbody>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" valign=\"top\" style=\"font-weight:bold\">\n"
                    + "                                                                    Name:</td>\n"
                    + "                                                                <td width=\"75%\" valign=\"top\">" + orderDTO.getFullName() + "</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td style=\"font-weight:bold\">Address:</td>\n"
                    + "                                                                <td>" + orderDTO.getOrderAddress() + "</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td valign=\"top\" style=\"font-weight:bold\">Phone:\n"
                    + "                                                                </td>\n"
                    + "                                                                <td valign=\"top\">" + orderDTO.getPhoneNumber() + "</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                        </tbody>\n"
                    + "                                                    </table>\n"
                    + "                                                </td>\n"
                    + "                                            </tr>\n"
                    + "                                        </tbody>\n"
                    + "                                    </table>\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"30\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"margin-top: 30px;background:#f0f0f0;height: 9.6px\">\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                        </tbody>\n"
                    + "                    </table>\n"
                    + "                    <!-- END DELIVERY DETAILS -->\n"
                    + "                    <!-- START ORDER DETAIL -->\n"
                    + "                    <table lang=\"header\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\" style=\"width:100%\">\n"
                    + "                        <tbody>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"70\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"padding:30px;background:#ffffff;height:70px\">\n"
                    + "                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" height=\"70\" border=\"0\"\n"
                    + "                                        style=\"width:100%;height:70px\">\n"
                    + "                                        <tbody>\n"
                    + "                                            <tr>\n"
                    + "                                                <td\n"
                    + "                                                    style=\"font-family: 'Raleway', sans-serif; font-size:20px; color:#2a3a4b;\">\n"
                    + "                                                    <span><img\n"
                    + "                                                            src=\"https://ci6.googleusercontent.com/proxy/qpkhQlR635Jxtsrpbga7PYZv2NCz1935N6GJKvi4CK1fxYt7xAa4Yu9lDzjgJz6236kxpfXX0P-uyKCcHobCgHegZzVMjgQd3WfSiad2pcH1h58=s0-d-e1-ft#https://img.alicdn.com/tfs/TB1Y5JLyhn1gK0jSZKPXXXvUXXa-30-30.png\"\n"
                    + "                                                            alt=\"icon\"\n"
                    + "                                                            style=\"height: 29px; vertical-align:middle\"></span>\n"
                    + "                                                    <div style=\"display: inline-block; margin-left: 7px;\">Order summary\n"
                    + "                                                    </div>\n"
                    + "                                                </td>\n"
                    + "                                            </tr>\n"
                    + "                                            <tr>\n"
                    + "                                                <td height=\"15\"></td>\n"
                    + "                                            </tr>\n"
                    + "                                            <tr>\n"
                    + "                                                <td>\n"
                    + "                                                    <table cellpadding=\"2\" cellspacing=\"0\" width=\"100%\">\n"
                    + "                                                        <tbody>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Payment Method:</td>\n"
                    + "                                                                <td width=\"75%\" style=\"text-align: end;\">Cash On\n"
                    + "                                                                    Delivery</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Quantity:</td>\n"
                    + "                                                                <td width=\"75%\" style=\"text-align: end;\"> " + quantity + "</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Subtotal:</td>\n"
                    + "                                                                <td width=\"75%\" style=\"text-align: end;\"> " + subtotal + "₫</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Shipping fee:</td>\n"
                    + "                                                                <td width=\"75%\" style=\"text-align: end;\"> " + orderDTO.getShippingFee() + "₫</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td colspan=\"2\">\n"
                    + "                                                                    <hr>\n"
                    + "                                                                </td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Total:</td>\n"
                    + "                                                                <td width=\"75%\"\n"
                    + "                                                                    style=\"text-align: end; font-weight:bold; color:#499B14\">\n"
                    + "                                                                    " + total + "₫</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                        </tbody>\n"
                    + "                                                    </table>\n"
                    + "                                                </td>\n"
                    + "                                            </tr>\n"
                    + "                                        </tbody>\n"
                    + "                                    </table>\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"30\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"margin-top: 30px;background:#f0f0f0;height: 9.6px\">\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                        </tbody>\n"
                    + "                    </table>\n"
                    + "                    <!-- END ORDER DETAILS -->\n"
                    + "                </td>\n"
                    + "                <td bgcolor=\"#B3B3B3\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#D1D1D1\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#E8E8E8\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#F8F8F8\" width=\"1px\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#B3B3B3\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#D1D1D1\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#E8E8E8\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#F8F8F8\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "        </tbody>\n"
                    + "    </table>\n"
                    + "</div>",
                    "UTF-8", "html");
            //Gửi email
            Transport.send(msg);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean sendEmailGotOrderToGuest(OrderDTO orderDTO) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMPT HOST
        props.put("mail.smtp.port", "587"); //TLS 587 SSL465
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        //calculate total and subtotal
        double subtotal = 0;
        double total = 0;
        int quantity = 0;
        for (ProductDTO product : orderDTO.getProductsList()) {
            subtotal += product.getPrice() * product.getQuantity();
            quantity += product.getQuantity();
        }
        total = subtotal + orderDTO.getShippingFee();
        // Create authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM, PASSWORD);
            }
        };
        Session session = Session.getInstance(props, auth);
        MimeMessage msg = new MimeMessage(session);
        try {
            //Kiểu nội dung
            msg.addHeader("Content-type", "text/html; charset=UTF-8");
            //Ng gửi
            msg.setFrom(FROM);

            //Ng nhận
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(orderDTO.getEmail(), false));

            //Tiêu đề
            msg.setSubject("Your order is waiting to confirm!");

            //Quy định email nhận phản hồi
            //msg.setReplyTo()
            //Nội dung
            msg.setText("<div align=\"center\">\n"
                    + "    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:730px\">\n"
                    + "        <tbody>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#E8E8E8\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td bgcolor=\"#F8F8F8\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#E8E8E8\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#D1D1D1\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#FFF\">\n"
                    + "                    <table lang=\"header\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\" style=\"width:100%\">\n"
                    + "                        <tbody>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"70\" valign=\"middle\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"background:#ffffff;height:70\">\n"
                    + "                                    <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" height=\"70\" border=\"0\">\n"
                    + "                                        <tbody>\n"
                    + "                                          <td width=\"100px\"></td>"
                    + "                                            <td align=\"\"\n"
                    + "                                                style=\"font-family: 'Raleway', sans-serif; font-size:34px; color:#499B14; line-height:24px; font-weight: bold; letter-spacing: 1px;  padding-top: 30px;\">\n"
                    + "                                                <span><img\n"
                    + "                                                        src=\"https://lh3.googleusercontent.com/pw/AIL4fc-er2uf7hkMZyYkuvSFfPF7QX9cLbjBt1ACdCa4gc4P0yUicrt-4JvvRI9Rl07p4hbmUDiz6qjMLfINSxuRFJJ503WBWB4NCHgTcrCEN3fuSs4FvD-ueJUqI1agFyQF_1Qrsrh3knuzwbxpC7xSCuGzEpwolK9EkyQH2fJBmWxG_4MGqqb_dLvAIxaOvRnoyeybKKojoSa8PZRv3wPQFNhz0p2JZZpnHzbYyalIzzyClgrL4HX59ZBGEMKKTx4M-4xcPtXhBD-iBDen_xyzhqC_Ba_lXx2OJ3IYyGxeYgIkmBffxv6eJ01dwF54YoULwyajlagTRSQX85Ecv6KM0PQJXaMzhJoGvdA9CISDTxQcV6QTeePNNYH3qedqpKby5zwbc3jqU6J-9DTpeemo3PHw73KYkRH0GLnktP4cmQottwxw8HMwiwG7wBFNb9fbdgDvi1TCf5d1V-XQDY1pbY7eQUb2zqGcVuIi6U00SONfqTrnBtHWIojoDDmyNvJ5PIzLJuNHKD5uz84FHHjEfqcVf1TZV7RczBKwga5h2qat2zWHjy_bl3n3CqIWCOEwUoGRBk2RRXOLzk3KnNKKiv2kRHkXj8N-Ststw0aJZBIhpaUtU8BFjC1-MMsB72b-xYPiYCZ1DImSjT7DDU9DynyhUyoiv1JWKBAORtKV2WWZJk4f87ukwUwSNWUImJjwWy7-fkUEsNpDqxjC8HVNlCCtLce1w7wqLONq24OhMT4TwsGGNkT7Ell8p4zeVYSEOmdRyhiEjmQRZSPTB0b_-Sj2TPCBFXL8FwbiVmQFaixa2hB62bdNamsI0ZEuoRID6dd-QpyNv0tJU9e9UaN2Te199q5qx565y0xrJ5Xgwl6Mt4Y9BteFmgSsgyaHsmvatKJObzuQ2Mqu4RBiiQZgnXA3dLG6LJTivalH6kaGTQVc5a9B7I8-Io__SZk=s250-k-rw-no\"\n"
                    + "                                                        alt=\"icon\"\n"
                    + "                                                        style=\"width:100%;max-width:79px;vertical-align:middle\"></span>\n"
                    + "                                                Bird Meal Order Shop\n"
                    + "                                            </td>\n"
                    + "                                        </tbody>\n"
                    + "                                    </table>\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td height=\"18\"></td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td align=\"center\"\n"
                    + "                                    style=\"font-family: 'Raleway', sans-serif; font-size:22px; font-weight: bold; color:#2a3a4b;\">\n"
                    + "                                    Thank you for your order!</td>\n"
                    + "                            </tr>\n"
                    + "                        </tbody>\n"
                    + "                    </table>\n"
                    + "                    <table lang=\"header\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\" style=\"width:100%\">\n"
                    + "                        <tbody>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"70\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"padding:20px 30px 30px 30px;background:#ffffff;height:70px\">\n"
                    + "                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" height=\"70\" border=\"0\"\n"
                    + "                                        style=\"width:100%;height:70px\">\n"
                    + "                                        <tbody>\n"
                    + "                                            <tr>\n"
                    + "                                                <td>\n"
                    + "                                                    <h3>Hello " + orderDTO.getFullName() + ",</h3>\n"
                    + "                                                    <p>Your order #" + orderDTO.getOrderID() + " has been received and is being processed. We\n"
                    + "                                                        will\n"
                    + "                                                        notify you\n"
                    + "                                                        you when the order is confirmed.</p>\n"
                    + "                                                </td>\n"
                    + "                                        </tbody>\n"
                    + "                                    </table>\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"30\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"margin-top: 30px;background:#f0f0f0;height: 9.6px\">\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                        </tbody>\n"
                    + "                    </table>\n"
                    + "                    <!-- START DELIVERY DETAILS -->\n"
                    + "                    <table lang=\"header\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\" style=\"width:100%\">\n"
                    + "                        <tbody>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"70\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"padding:30px;background:#ffffff;height:70px\">\n"
                    + "                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" height=\"70\" border=\"0\"\n"
                    + "                                        style=\"width:100%;height:70px\">\n"
                    + "                                        <tbody>\n"
                    + "                                            <tr>\n"
                    + "                                                <td\n"
                    + "                                                    style=\"font-family: 'Raleway', sans-serif; font-size:20px; color:#2a3a4b;\">\n"
                    + "                                                    <span><img\n"
                    + "                                                            src=\"https://ci6.googleusercontent.com/proxy/AUmakQI121QQ_WvdspszI-42jmht-u29A4iIvgfX5V-q3Z9qyUAfpUFNiqpGKP4CW6L1x8cnoxHpjGrhiuLmDr8J92EgI9gpuNCZdjW70wzcGuk=s0-d-e1-ft#https://img.alicdn.com/tfs/TB1ciNPybj1gK0jSZFOXXc7GpXa-48-48.png\"\n"
                    + "                                                            alt=\"icon\"\n"
                    + "                                                            style=\"height: 29px; vertical-align:middle\"></span>\n"
                    + "                                                    <div style=\"display: inline-block; margin-left: 7px;\">Delivery\n"
                    + "                                                        details</div>\n"
                    + "                                                </td>\n"
                    + "                                            </tr>\n"
                    + "                                            <tr>\n"
                    + "                                                <td height=\"15\"></td>\n"
                    + "                                            </tr>\n"
                    + "                                            <tr>\n"
                    + "                                                <td>\n"
                    + "                                                    <table cellpadding=\"2\" cellspacing=\"0\" width=\"100%\">\n"
                    + "                                                        <tbody>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" valign=\"top\" style=\"font-weight:bold\">\n"
                    + "                                                                    Name:</td>\n"
                    + "                                                                <td width=\"75%\" valign=\"top\">" + orderDTO.getFullName() + "</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td style=\"font-weight:bold\">Address:</td>\n"
                    + "                                                                <td>" + orderDTO.getOrderAddress() + "</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td valign=\"top\" style=\"font-weight:bold\">Phone:\n"
                    + "                                                                </td>\n"
                    + "                                                                <td valign=\"top\">" + orderDTO.getPhoneNumber() + "</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                        </tbody>\n"
                    + "                                                    </table>\n"
                    + "                                                </td>\n"
                    + "                                            </tr>\n"
                    + "                                        </tbody>\n"
                    + "                                    </table>\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"30\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"margin-top: 30px;background:#f0f0f0;height: 9.6px\">\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                        </tbody>\n"
                    + "                    </table>\n"
                    + "                    <!-- END DELIVERY DETAILS -->\n"
                    + "                    <!-- START ORDER DETAIL -->\n"
                    + "                    <table lang=\"header\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\" style=\"width:100%\">\n"
                    + "                        <tbody>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"70\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"padding:30px;background:#ffffff;height:70px\">\n"
                    + "                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" height=\"70\" border=\"0\"\n"
                    + "                                        style=\"width:100%;height:70px\">\n"
                    + "                                        <tbody>\n"
                    + "                                            <tr>\n"
                    + "                                                <td\n"
                    + "                                                    style=\"font-family: 'Raleway', sans-serif; font-size:20px; color:#2a3a4b;\">\n"
                    + "                                                    <span><img\n"
                    + "                                                            src=\"https://ci6.googleusercontent.com/proxy/qpkhQlR635Jxtsrpbga7PYZv2NCz1935N6GJKvi4CK1fxYt7xAa4Yu9lDzjgJz6236kxpfXX0P-uyKCcHobCgHegZzVMjgQd3WfSiad2pcH1h58=s0-d-e1-ft#https://img.alicdn.com/tfs/TB1Y5JLyhn1gK0jSZKPXXXvUXXa-30-30.png\"\n"
                    + "                                                            alt=\"icon\"\n"
                    + "                                                            style=\"height: 29px; vertical-align:middle\"></span>\n"
                    + "                                                    <div style=\"display: inline-block; margin-left: 7px;\">Order summary\n"
                    + "                                                    </div>\n"
                    + "                                                </td>\n"
                    + "                                            </tr>\n"
                    + "                                            <tr>\n"
                    + "                                                <td height=\"15\"></td>\n"
                    + "                                            </tr>\n"
                    + "                                            <tr>\n"
                    + "                                                <td>\n"
                    + "                                                    <table cellpadding=\"2\" cellspacing=\"0\" width=\"100%\">\n"
                    + "                                                        <tbody>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Payment Method:</td>\n"
                    + "                                                                <td width=\"75%\" style=\"text-align: end;\">Cash On\n"
                    + "                                                                    Delivery</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Quantity:</td>\n"
                    + "                                                                <td width=\"75%\" style=\"text-align: end;\"> " + quantity + "</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Subtotal:</td>\n"
                    + "                                                                <td width=\"75%\" style=\"text-align: end;\"> " + subtotal + "₫</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Shipping fee:</td>\n"
                    + "                                                                <td width=\"75%\" style=\"text-align: end;\"> " + orderDTO.getShippingFee() + "₫</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td colspan=\"2\">\n"
                    + "                                                                    <hr>\n"
                    + "                                                                </td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Total:</td>\n"
                    + "                                                                <td width=\"75%\"\n"
                    + "                                                                    style=\"text-align: end; font-weight:bold; color:#499B14\">\n"
                    + "                                                                    " + total + "₫</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                        </tbody>\n"
                    + "                                                    </table>\n"
                    + "                                                </td>\n"
                    + "                                            </tr>\n"
                    + "                                        </tbody>\n"
                    + "                                    </table>\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"30\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"margin-top: 30px;background:#f0f0f0;height: 9.6px\">\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                        </tbody>\n"
                    + "                    </table>\n"
                    + "                    <!-- END ORDER DETAILS -->\n"
                    + "                </td>\n"
                    + "                <td bgcolor=\"#B3B3B3\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#D1D1D1\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#E8E8E8\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#F8F8F8\" width=\"1px\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#B3B3B3\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#D1D1D1\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#E8E8E8\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#F8F8F8\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "        </tbody>\n"
                    + "    </table>\n"
                    + "</div>",
                    "UTF-8", "html");
            //Gửi email
            Transport.send(msg);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean sendEmailConfirmOrderToGuest(OrderDTO orderDTO) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMPT HOST
        props.put("mail.smtp.port", "587"); //TLS 587 SSL465
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        //calculate total and subtotal
        double subtotal = 0;
        double total = 0;
        int quantity = 0;
        for (ProductDTO product : orderDTO.getProductsList()) {
            subtotal += product.getPrice() * product.getQuantity();
            quantity += product.getQuantity();
        }
        total = subtotal + orderDTO.getShippingFee();
        // Create authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM, PASSWORD);
            }
        };
        Session session = Session.getInstance(props, auth);
        MimeMessage msg = new MimeMessage(session);
        try {
            //Kiểu nội dung
            msg.addHeader("Content-type", "text/html; charset=UTF-8");
            //Ng gửi
            msg.setFrom(FROM);

            //Ng nhận
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(orderDTO.getEmail(), false));

            //Tiêu đề
            msg.setSubject("Your order has been confirmed");

            //Quy định email nhận phản hồi
            //msg.setReplyTo()
            //Nội dung
            msg.setText("<div align=\"center\">\n"
                    + "    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:730px\">\n"
                    + "        <tbody>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#E8E8E8\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td bgcolor=\"#F8F8F8\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#E8E8E8\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#D1D1D1\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#FFF\">\n"
                    + "                    <table lang=\"header\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\" style=\"width:100%\">\n"
                    + "                        <tbody>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"70\" valign=\"middle\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"background:#ffffff;height:70\">\n"
                    + "                                    <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" height=\"70\" border=\"0\">\n"
                    + "                                        <tbody>\n"
                    + "                                          <td width=\"100px\"></td>"
                    + "                                            <td align=\"\"\n"
                    + "                                                style=\"font-family: 'Raleway', sans-serif; font-size:34px; color:#499B14; line-height:24px; font-weight: bold; letter-spacing: 1px;  padding-top: 30px;\">\n"
                    + "                                                <span><img\n"
                    + "                                                        src=\"https://lh3.googleusercontent.com/pw/AIL4fc-er2uf7hkMZyYkuvSFfPF7QX9cLbjBt1ACdCa4gc4P0yUicrt-4JvvRI9Rl07p4hbmUDiz6qjMLfINSxuRFJJ503WBWB4NCHgTcrCEN3fuSs4FvD-ueJUqI1agFyQF_1Qrsrh3knuzwbxpC7xSCuGzEpwolK9EkyQH2fJBmWxG_4MGqqb_dLvAIxaOvRnoyeybKKojoSa8PZRv3wPQFNhz0p2JZZpnHzbYyalIzzyClgrL4HX59ZBGEMKKTx4M-4xcPtXhBD-iBDen_xyzhqC_Ba_lXx2OJ3IYyGxeYgIkmBffxv6eJ01dwF54YoULwyajlagTRSQX85Ecv6KM0PQJXaMzhJoGvdA9CISDTxQcV6QTeePNNYH3qedqpKby5zwbc3jqU6J-9DTpeemo3PHw73KYkRH0GLnktP4cmQottwxw8HMwiwG7wBFNb9fbdgDvi1TCf5d1V-XQDY1pbY7eQUb2zqGcVuIi6U00SONfqTrnBtHWIojoDDmyNvJ5PIzLJuNHKD5uz84FHHjEfqcVf1TZV7RczBKwga5h2qat2zWHjy_bl3n3CqIWCOEwUoGRBk2RRXOLzk3KnNKKiv2kRHkXj8N-Ststw0aJZBIhpaUtU8BFjC1-MMsB72b-xYPiYCZ1DImSjT7DDU9DynyhUyoiv1JWKBAORtKV2WWZJk4f87ukwUwSNWUImJjwWy7-fkUEsNpDqxjC8HVNlCCtLce1w7wqLONq24OhMT4TwsGGNkT7Ell8p4zeVYSEOmdRyhiEjmQRZSPTB0b_-Sj2TPCBFXL8FwbiVmQFaixa2hB62bdNamsI0ZEuoRID6dd-QpyNv0tJU9e9UaN2Te199q5qx565y0xrJ5Xgwl6Mt4Y9BteFmgSsgyaHsmvatKJObzuQ2Mqu4RBiiQZgnXA3dLG6LJTivalH6kaGTQVc5a9B7I8-Io__SZk=s250-k-rw-no\"\n"
                    + "                                                        alt=\"icon\"\n"
                    + "                                                        style=\"width:100%;max-width:79px;vertical-align:middle\"></span>\n"
                    + "                                                Bird Meal Order Shop\n"
                    + "                                            </td>\n"
                    + "                                        </tbody>\n"
                    + "                                    </table>\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td height=\"18\"></td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td align=\"center\"\n"
                    + "                                    style=\"font-family: 'Raleway', sans-serif; font-size:22px; font-weight: bold; color:#2a3a4b;\">\n"
                    + "                                    Thank you for your order!</td>\n"
                    + "                            </tr>\n"
                    + "                        </tbody>\n"
                    + "                    </table>\n"
                    + "                    <table lang=\"header\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\" style=\"width:100%\">\n"
                    + "                        <tbody>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"70\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"padding:20px 30px 30px 30px;background:#ffffff;height:70px\">\n"
                    + "                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" height=\"70\" border=\"0\"\n"
                    + "                                        style=\"width:100%;height:70px\">\n"
                    + "                                        <tbody>\n"
                    + "                                            <tr>\n"
                    + "                                                <td>\n"
                    + "                                                    <h3>Hello " + orderDTO.getFullName() + ",</h3>\n"
                    + "                                                    <p>Your order #" + orderDTO.getOrderID() + " has been received and is being processed. We\n"
                    + "                                                        will\n"
                    + "                                                        notify you\n"
                    + "                                                        when the parcel is ready.</p>\n"
                    + "                                                </td>\n"
                    + "                                        </tbody>\n"
                    + "                                    </table>\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"30\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"margin-top: 30px;background:#f0f0f0;height: 9.6px\">\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                        </tbody>\n"
                    + "                    </table>\n"
                    + "                    <!-- START DELIVERY DETAILS -->\n"
                    + "                    <table lang=\"header\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\" style=\"width:100%\">\n"
                    + "                        <tbody>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"70\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"padding:30px;background:#ffffff;height:70px\">\n"
                    + "                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" height=\"70\" border=\"0\"\n"
                    + "                                        style=\"width:100%;height:70px\">\n"
                    + "                                        <tbody>\n"
                    + "                                            <tr>\n"
                    + "                                                <td\n"
                    + "                                                    style=\"font-family: 'Raleway', sans-serif; font-size:20px; color:#2a3a4b;\">\n"
                    + "                                                    <span><img\n"
                    + "                                                            src=\"https://ci6.googleusercontent.com/proxy/AUmakQI121QQ_WvdspszI-42jmht-u29A4iIvgfX5V-q3Z9qyUAfpUFNiqpGKP4CW6L1x8cnoxHpjGrhiuLmDr8J92EgI9gpuNCZdjW70wzcGuk=s0-d-e1-ft#https://img.alicdn.com/tfs/TB1ciNPybj1gK0jSZFOXXc7GpXa-48-48.png\"\n"
                    + "                                                            alt=\"icon\"\n"
                    + "                                                            style=\"height: 29px; vertical-align:middle\"></span>\n"
                    + "                                                    <div style=\"display: inline-block; margin-left: 7px;\">Delivery\n"
                    + "                                                        details</div>\n"
                    + "                                                </td>\n"
                    + "                                            </tr>\n"
                    + "                                            <tr>\n"
                    + "                                                <td height=\"15\"></td>\n"
                    + "                                            </tr>\n"
                    + "                                            <tr>\n"
                    + "                                                <td>\n"
                    + "                                                    <table cellpadding=\"2\" cellspacing=\"0\" width=\"100%\">\n"
                    + "                                                        <tbody>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" valign=\"top\" style=\"font-weight:bold\">\n"
                    + "                                                                    Name:</td>\n"
                    + "                                                                <td width=\"75%\" valign=\"top\">" + orderDTO.getFullName() + "</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td style=\"font-weight:bold\">Address:</td>\n"
                    + "                                                                <td>" + orderDTO.getOrderAddress() + "</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td valign=\"top\" style=\"font-weight:bold\">Phone:\n"
                    + "                                                                </td>\n"
                    + "                                                                <td valign=\"top\">" + orderDTO.getPhoneNumber() + "</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                        </tbody>\n"
                    + "                                                    </table>\n"
                    + "                                                </td>\n"
                    + "                                            </tr>\n"
                    + "                                        </tbody>\n"
                    + "                                    </table>\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"30\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"margin-top: 30px;background:#f0f0f0;height: 9.6px\">\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                        </tbody>\n"
                    + "                    </table>\n"
                    + "                    <!-- END DELIVERY DETAILS -->\n"
                    + "                    <!-- START ORDER DETAIL -->\n"
                    + "                    <table lang=\"header\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\" style=\"width:100%\">\n"
                    + "                        <tbody>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"70\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"padding:30px;background:#ffffff;height:70px\">\n"
                    + "                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" height=\"70\" border=\"0\"\n"
                    + "                                        style=\"width:100%;height:70px\">\n"
                    + "                                        <tbody>\n"
                    + "                                            <tr>\n"
                    + "                                                <td\n"
                    + "                                                    style=\"font-family: 'Raleway', sans-serif; font-size:20px; color:#2a3a4b;\">\n"
                    + "                                                    <span><img\n"
                    + "                                                            src=\"https://ci6.googleusercontent.com/proxy/qpkhQlR635Jxtsrpbga7PYZv2NCz1935N6GJKvi4CK1fxYt7xAa4Yu9lDzjgJz6236kxpfXX0P-uyKCcHobCgHegZzVMjgQd3WfSiad2pcH1h58=s0-d-e1-ft#https://img.alicdn.com/tfs/TB1Y5JLyhn1gK0jSZKPXXXvUXXa-30-30.png\"\n"
                    + "                                                            alt=\"icon\"\n"
                    + "                                                            style=\"height: 29px; vertical-align:middle\"></span>\n"
                    + "                                                    <div style=\"display: inline-block; margin-left: 7px;\">Order summary\n"
                    + "                                                    </div>\n"
                    + "                                                </td>\n"
                    + "                                            </tr>\n"
                    + "                                            <tr>\n"
                    + "                                                <td height=\"15\"></td>\n"
                    + "                                            </tr>\n"
                    + "                                            <tr>\n"
                    + "                                                <td>\n"
                    + "                                                    <table cellpadding=\"2\" cellspacing=\"0\" width=\"100%\">\n"
                    + "                                                        <tbody>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Payment Method:</td>\n"
                    + "                                                                <td width=\"75%\" style=\"text-align: end;\">Cash On\n"
                    + "                                                                    Delivery</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Quantity:</td>\n"
                    + "                                                                <td width=\"75%\" style=\"text-align: end;\"> " + quantity + "</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Subtotal:</td>\n"
                    + "                                                                <td width=\"75%\" style=\"text-align: end;\"> " + subtotal + "₫</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Shipping fee:</td>\n"
                    + "                                                                <td width=\"75%\" style=\"text-align: end;\"> " + orderDTO.getShippingFee() + "₫</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td colspan=\"2\">\n"
                    + "                                                                    <hr>\n"
                    + "                                                                </td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Total:</td>\n"
                    + "                                                                <td width=\"75%\"\n"
                    + "                                                                    style=\"text-align: end; font-weight:bold; color:#499B14\">\n"
                    + "                                                                    " + total + "₫</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                        </tbody>\n"
                    + "                                                    </table>\n"
                    + "                                                </td>\n"
                    + "                                            </tr>\n"
                    + "                                        </tbody>\n"
                    + "                                    </table>\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"30\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"margin-top: 30px;background:#f0f0f0;height: 9.6px\">\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                        </tbody>\n"
                    + "                    </table>\n"
                    + "                    <!-- END ORDER DETAILS -->\n"
                    + "                </td>\n"
                    + "                <td bgcolor=\"#B3B3B3\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#D1D1D1\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#E8E8E8\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#F8F8F8\" width=\"1px\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#B3B3B3\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#D1D1D1\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#E8E8E8\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#F8F8F8\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "        </tbody>\n"
                    + "    </table>\n"
                    + "</div>",
                    "UTF-8", "html");
            //Gửi email
            Transport.send(msg);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean sendEmailOrderIsShippedToGuest(OrderDTO orderDTO) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMPT HOST
        props.put("mail.smtp.port", "587"); //TLS 587 SSL465
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        //calculate total and subtotal
        double subtotal = 0;
        double total = 0;
        int quantity = 0;
        for (ProductDTO product : orderDTO.getProductsList()) {
            subtotal += product.getPrice() * product.getQuantity();
            quantity += product.getQuantity();
        }
        total = subtotal + orderDTO.getShippingFee();
        // Create authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM, PASSWORD);
            }
        };
        Session session = Session.getInstance(props, auth);
        MimeMessage msg = new MimeMessage(session);
        try {
            //Kiểu nội dung
            msg.addHeader("Content-type", "text/html; charset=UTF-8");
            //Ng gửi
            msg.setFrom(FROM);

            //Ng nhận
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(orderDTO.getEmail(), false));

            //Tiêu đề
            msg.setSubject("Your parcel is being delivered");

            //Quy định email nhận phản hồi
            //msg.setReplyTo()
            //Nội dung
            msg.setText("<div align=\"center\">\n"
                    + "    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:730px\">\n"
                    + "        <tbody>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#E8E8E8\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td bgcolor=\"#F8F8F8\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#E8E8E8\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#D1D1D1\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#FFF\">\n"
                    + "                    <table lang=\"header\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\" style=\"width:100%\">\n"
                    + "                        <tbody>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"70\" valign=\"middle\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"background:#ffffff;height:70\">\n"
                    + "                                    <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" height=\"70\" border=\"0\">\n"
                    + "                                        <tbody>\n"
                    + "                                          <td width=\"100px\"></td>"
                    + "                                            <td align=\"\"\n"
                    + "                                                style=\"font-family: 'Raleway', sans-serif; font-size:34px; color:#499B14; line-height:24px; font-weight: bold; letter-spacing: 1px;  padding-top: 30px;\">\n"
                    + "                                                <span><img\n"
                    + "                                                        src=\"https://lh3.googleusercontent.com/pw/AIL4fc-er2uf7hkMZyYkuvSFfPF7QX9cLbjBt1ACdCa4gc4P0yUicrt-4JvvRI9Rl07p4hbmUDiz6qjMLfINSxuRFJJ503WBWB4NCHgTcrCEN3fuSs4FvD-ueJUqI1agFyQF_1Qrsrh3knuzwbxpC7xSCuGzEpwolK9EkyQH2fJBmWxG_4MGqqb_dLvAIxaOvRnoyeybKKojoSa8PZRv3wPQFNhz0p2JZZpnHzbYyalIzzyClgrL4HX59ZBGEMKKTx4M-4xcPtXhBD-iBDen_xyzhqC_Ba_lXx2OJ3IYyGxeYgIkmBffxv6eJ01dwF54YoULwyajlagTRSQX85Ecv6KM0PQJXaMzhJoGvdA9CISDTxQcV6QTeePNNYH3qedqpKby5zwbc3jqU6J-9DTpeemo3PHw73KYkRH0GLnktP4cmQottwxw8HMwiwG7wBFNb9fbdgDvi1TCf5d1V-XQDY1pbY7eQUb2zqGcVuIi6U00SONfqTrnBtHWIojoDDmyNvJ5PIzLJuNHKD5uz84FHHjEfqcVf1TZV7RczBKwga5h2qat2zWHjy_bl3n3CqIWCOEwUoGRBk2RRXOLzk3KnNKKiv2kRHkXj8N-Ststw0aJZBIhpaUtU8BFjC1-MMsB72b-xYPiYCZ1DImSjT7DDU9DynyhUyoiv1JWKBAORtKV2WWZJk4f87ukwUwSNWUImJjwWy7-fkUEsNpDqxjC8HVNlCCtLce1w7wqLONq24OhMT4TwsGGNkT7Ell8p4zeVYSEOmdRyhiEjmQRZSPTB0b_-Sj2TPCBFXL8FwbiVmQFaixa2hB62bdNamsI0ZEuoRID6dd-QpyNv0tJU9e9UaN2Te199q5qx565y0xrJ5Xgwl6Mt4Y9BteFmgSsgyaHsmvatKJObzuQ2Mqu4RBiiQZgnXA3dLG6LJTivalH6kaGTQVc5a9B7I8-Io__SZk=s250-k-rw-no\"\n"
                    + "                                                        alt=\"icon\"\n"
                    + "                                                        style=\"width:100%;max-width:79px;vertical-align:middle\"></span>\n"
                    + "                                                Bird Meal Order Shop\n"
                    + "                                            </td>\n"
                    + "                                        </tbody>\n"
                    + "                                    </table>\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td height=\"18\"></td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td align=\"center\"\n"
                    + "                                    style=\"font-family: 'Raleway', sans-serif; font-size:22px; font-weight: bold; color:#2a3a4b;\">\n"
                    + "                                    The parcel is being delivered</td>\n"
                    + "                            </tr>\n"
                    + "                        </tbody>\n"
                    + "                    </table>\n"
                    + "                    <table lang=\"header\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\" style=\"width:100%\">\n"
                    + "                        <tbody>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"70\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"padding:20px 30px 30px 30px;background:#ffffff;height:70px\">\n"
                    + "                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" height=\"70\" border=\"0\"\n"
                    + "                                        style=\"width:100%;height:70px\">\n"
                    + "                                        <tbody>\n"
                    + "                                            <tr>\n"
                    + "                                                <td>\n"
                    + "                                                    <h3>Hello " + orderDTO.getFullName() + ",</h3>\n"
                    + "                                                    <p>Your order #" + orderDTO.getOrderID() + " has been handed over to the shipping unit. You could receive it in a few days. Please prepare " + total + "VND to pay.</p>\n"
                    + "                                                </td>\n"
                    + "                                        </tbody>\n"
                    + "                                    </table>\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"30\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"margin-top: 30px;background:#f0f0f0;height: 9.6px\">\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                        </tbody>\n"
                    + "                    </table>\n"
                    + "                    <!-- START DELIVERY DETAILS -->\n"
                    + "                    <table lang=\"header\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\" style=\"width:100%\">\n"
                    + "                        <tbody>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"70\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"padding:30px;background:#ffffff;height:70px\">\n"
                    + "                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" height=\"70\" border=\"0\"\n"
                    + "                                        style=\"width:100%;height:70px\">\n"
                    + "                                        <tbody>\n"
                    + "                                            <tr>\n"
                    + "                                                <td\n"
                    + "                                                    style=\"font-family: 'Raleway', sans-serif; font-size:20px; color:#2a3a4b;\">\n"
                    + "                                                    <span><img\n"
                    + "                                                            src=\"https://ci6.googleusercontent.com/proxy/AUmakQI121QQ_WvdspszI-42jmht-u29A4iIvgfX5V-q3Z9qyUAfpUFNiqpGKP4CW6L1x8cnoxHpjGrhiuLmDr8J92EgI9gpuNCZdjW70wzcGuk=s0-d-e1-ft#https://img.alicdn.com/tfs/TB1ciNPybj1gK0jSZFOXXc7GpXa-48-48.png\"\n"
                    + "                                                            alt=\"icon\"\n"
                    + "                                                            style=\"height: 29px; vertical-align:middle\"></span>\n"
                    + "                                                    <div style=\"display: inline-block; margin-left: 7px;\">Delivery\n"
                    + "                                                        details</div>\n"
                    + "                                                </td>\n"
                    + "                                            </tr>\n"
                    + "                                            <tr>\n"
                    + "                                                <td height=\"15\"></td>\n"
                    + "                                            </tr>\n"
                    + "                                            <tr>\n"
                    + "                                                <td>\n"
                    + "                                                    <table cellpadding=\"2\" cellspacing=\"0\" width=\"100%\">\n"
                    + "                                                        <tbody>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" valign=\"top\" style=\"font-weight:bold\">\n"
                    + "                                                                    Name:</td>\n"
                    + "                                                                <td width=\"75%\" valign=\"top\">" + orderDTO.getFullName() + "</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td style=\"font-weight:bold\">Address:</td>\n"
                    + "                                                                <td>" + orderDTO.getOrderAddress() + "</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td valign=\"top\" style=\"font-weight:bold\">Phone:\n"
                    + "                                                                </td>\n"
                    + "                                                                <td valign=\"top\">" + orderDTO.getPhoneNumber() + "</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                        </tbody>\n"
                    + "                                                    </table>\n"
                    + "                                                </td>\n"
                    + "                                            </tr>\n"
                    + "                                        </tbody>\n"
                    + "                                    </table>\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"30\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"margin-top: 30px;background:#f0f0f0;height: 9.6px\">\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                        </tbody>\n"
                    + "                    </table>\n"
                    + "                    <!-- END DELIVERY DETAILS -->\n"
                    + "                    <!-- START ORDER DETAIL -->\n"
                    + "                    <table lang=\"header\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\" style=\"width:100%\">\n"
                    + "                        <tbody>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"70\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"padding:30px;background:#ffffff;height:70px\">\n"
                    + "                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" height=\"70\" border=\"0\"\n"
                    + "                                        style=\"width:100%;height:70px\">\n"
                    + "                                        <tbody>\n"
                    + "                                            <tr>\n"
                    + "                                                <td\n"
                    + "                                                    style=\"font-family: 'Raleway', sans-serif; font-size:20px; color:#2a3a4b;\">\n"
                    + "                                                    <span><img\n"
                    + "                                                            src=\"https://ci6.googleusercontent.com/proxy/qpkhQlR635Jxtsrpbga7PYZv2NCz1935N6GJKvi4CK1fxYt7xAa4Yu9lDzjgJz6236kxpfXX0P-uyKCcHobCgHegZzVMjgQd3WfSiad2pcH1h58=s0-d-e1-ft#https://img.alicdn.com/tfs/TB1Y5JLyhn1gK0jSZKPXXXvUXXa-30-30.png\"\n"
                    + "                                                            alt=\"icon\"\n"
                    + "                                                            style=\"height: 29px; vertical-align:middle\"></span>\n"
                    + "                                                    <div style=\"display: inline-block; margin-left: 7px;\">Order summary\n"
                    + "                                                    </div>\n"
                    + "                                                </td>\n"
                    + "                                            </tr>\n"
                    + "                                            <tr>\n"
                    + "                                                <td height=\"15\"></td>\n"
                    + "                                            </tr>\n"
                    + "                                            <tr>\n"
                    + "                                                <td>\n"
                    + "                                                    <table cellpadding=\"2\" cellspacing=\"0\" width=\"100%\">\n"
                    + "                                                        <tbody>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Payment Method:</td>\n"
                    + "                                                                <td width=\"75%\" style=\"text-align: end;\">Cash On\n"
                    + "                                                                    Delivery</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Quantity:</td>\n"
                    + "                                                                <td width=\"75%\" style=\"text-align: end;\"> " + quantity + "</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Subtotal:</td>\n"
                    + "                                                                <td width=\"75%\" style=\"text-align: end;\"> " + subtotal + "₫</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Shipping fee:</td>\n"
                    + "                                                                <td width=\"75%\" style=\"text-align: end;\"> " + orderDTO.getShippingFee() + "₫</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td colspan=\"2\">\n"
                    + "                                                                    <hr>\n"
                    + "                                                                </td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Total:</td>\n"
                    + "                                                                <td width=\"75%\"\n"
                    + "                                                                    style=\"text-align: end; font-weight:bold; color:#499B14\">\n"
                    + "                                                                    " + total + "₫</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                        </tbody>\n"
                    + "                                                    </table>\n"
                    + "                                                </td>\n"
                    + "                                            </tr>\n"
                    + "                                        </tbody>\n"
                    + "                                    </table>\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"30\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"margin-top: 30px;background:#f0f0f0;height: 9.6px\">\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                        </tbody>\n"
                    + "                    </table>\n"
                    + "                    <!-- END ORDER DETAILS -->\n"
                    + "                </td>\n"
                    + "                <td bgcolor=\"#B3B3B3\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#D1D1D1\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#E8E8E8\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#F8F8F8\" width=\"1px\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#B3B3B3\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#D1D1D1\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#E8E8E8\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#F8F8F8\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "        </tbody>\n"
                    + "    </table>\n"
                    + "</div>",
                    "UTF-8", "html");
            //Gửi email
            Transport.send(msg);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean sendEmailOrderIsShippedNotSuccessfullyToGuest(OrderDTO orderDTO) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMPT HOST
        props.put("mail.smtp.port", "587"); //TLS 587 SSL465
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        //calculate total and subtotal
        double subtotal = 0;
        double total = 0;
        int quantity = 0;
        for (ProductDTO product : orderDTO.getProductsList()) {
            subtotal += product.getPrice() * product.getQuantity();
            quantity += product.getQuantity();
        }
        total = subtotal + orderDTO.getShippingFee();
        // Create authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM, PASSWORD);
            }
        };
        Session session = Session.getInstance(props, auth);
        MimeMessage msg = new MimeMessage(session);
        try {
            //Kiểu nội dung
            msg.addHeader("Content-type", "text/html; charset=UTF-8");
            //Ng gửi
            msg.setFrom(FROM);

            //Ng nhận
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(orderDTO.getEmail(), false));

            //Tiêu đề
            msg.setSubject("Your parcel was not delivered successfully");

            //Quy định email nhận phản hồi
            //msg.setReplyTo()
            //Nội dung
            msg.setText("<div align=\"center\">\n"
                    + "    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:730px\">\n"
                    + "        <tbody>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#E8E8E8\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td bgcolor=\"#F8F8F8\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#E8E8E8\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#D1D1D1\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#FFF\">\n"
                    + "                    <table lang=\"header\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\" style=\"width:100%\">\n"
                    + "                        <tbody>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"70\" valign=\"middle\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"background:#ffffff;height:70\">\n"
                    + "                                    <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" height=\"70\" border=\"0\">\n"
                    + "                                        <tbody>\n"
                    + "                                          <td width=\"100px\"></td>"
                    + "                                            <td align=\"\"\n"
                    + "                                                style=\"font-family: 'Raleway', sans-serif; font-size:34px; color:#499B14; line-height:24px; font-weight: bold; letter-spacing: 1px;  padding-top: 30px;\">\n"
                    + "                                                <span><img\n"
                    + "                                                        src=\"https://lh3.googleusercontent.com/pw/AIL4fc-er2uf7hkMZyYkuvSFfPF7QX9cLbjBt1ACdCa4gc4P0yUicrt-4JvvRI9Rl07p4hbmUDiz6qjMLfINSxuRFJJ503WBWB4NCHgTcrCEN3fuSs4FvD-ueJUqI1agFyQF_1Qrsrh3knuzwbxpC7xSCuGzEpwolK9EkyQH2fJBmWxG_4MGqqb_dLvAIxaOvRnoyeybKKojoSa8PZRv3wPQFNhz0p2JZZpnHzbYyalIzzyClgrL4HX59ZBGEMKKTx4M-4xcPtXhBD-iBDen_xyzhqC_Ba_lXx2OJ3IYyGxeYgIkmBffxv6eJ01dwF54YoULwyajlagTRSQX85Ecv6KM0PQJXaMzhJoGvdA9CISDTxQcV6QTeePNNYH3qedqpKby5zwbc3jqU6J-9DTpeemo3PHw73KYkRH0GLnktP4cmQottwxw8HMwiwG7wBFNb9fbdgDvi1TCf5d1V-XQDY1pbY7eQUb2zqGcVuIi6U00SONfqTrnBtHWIojoDDmyNvJ5PIzLJuNHKD5uz84FHHjEfqcVf1TZV7RczBKwga5h2qat2zWHjy_bl3n3CqIWCOEwUoGRBk2RRXOLzk3KnNKKiv2kRHkXj8N-Ststw0aJZBIhpaUtU8BFjC1-MMsB72b-xYPiYCZ1DImSjT7DDU9DynyhUyoiv1JWKBAORtKV2WWZJk4f87ukwUwSNWUImJjwWy7-fkUEsNpDqxjC8HVNlCCtLce1w7wqLONq24OhMT4TwsGGNkT7Ell8p4zeVYSEOmdRyhiEjmQRZSPTB0b_-Sj2TPCBFXL8FwbiVmQFaixa2hB62bdNamsI0ZEuoRID6dd-QpyNv0tJU9e9UaN2Te199q5qx565y0xrJ5Xgwl6Mt4Y9BteFmgSsgyaHsmvatKJObzuQ2Mqu4RBiiQZgnXA3dLG6LJTivalH6kaGTQVc5a9B7I8-Io__SZk=s250-k-rw-no\"\n"
                    + "                                                        alt=\"icon\"\n"
                    + "                                                        style=\"width:100%;max-width:79px;vertical-align:middle\"></span>\n"
                    + "                                                Bird Meal Order Shop\n"
                    + "                                            </td>\n"
                    + "                                        </tbody>\n"
                    + "                                    </table>\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td height=\"18\"></td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td align=\"center\"\n"
                    + "                                    style=\"font-family: 'Raleway', sans-serif; font-size:22px; font-weight: bold; color:#2a3a4b;\">\n"
                    + "                                    The parcel will return to our shop.</td>\n"
                    + "                            </tr>\n"
                    + "                        </tbody>\n"
                    + "                    </table>\n"
                    + "                    <table lang=\"header\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\" style=\"width:100%\">\n"
                    + "                        <tbody>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"70\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"padding:20px 30px 30px 30px;background:#ffffff;height:70px\">\n"
                    + "                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" height=\"70\" border=\"0\"\n"
                    + "                                        style=\"width:100%;height:70px\">\n"
                    + "                                        <tbody>\n"
                    + "                                            <tr>\n"
                    + "                                                <td>\n"
                    + "                                                    <h3>Hello " + orderDTO.getFullName() + ",</h3>\n"
                    + "                                                    <p>We regret that you did not receive your order #" + orderDTO.getOrderID() + ". It will return to our shop.</p>\n"
                    + "                                                </td>\n"
                    + "                                        </tbody>\n"
                    + "                                    </table>\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"30\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"margin-top: 30px;background:#f0f0f0;height: 9.6px\">\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                        </tbody>\n"
                    + "                    </table>\n"
                    + "                    <!-- START DELIVERY DETAILS -->\n"
                    + "                    <table lang=\"header\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\" style=\"width:100%\">\n"
                    + "                        <tbody>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"70\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"padding:30px;background:#ffffff;height:70px\">\n"
                    + "                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" height=\"70\" border=\"0\"\n"
                    + "                                        style=\"width:100%;height:70px\">\n"
                    + "                                        <tbody>\n"
                    + "                                            <tr>\n"
                    + "                                                <td\n"
                    + "                                                    style=\"font-family: 'Raleway', sans-serif; font-size:20px; color:#2a3a4b;\">\n"
                    + "                                                    <span><img\n"
                    + "                                                            src=\"https://ci6.googleusercontent.com/proxy/AUmakQI121QQ_WvdspszI-42jmht-u29A4iIvgfX5V-q3Z9qyUAfpUFNiqpGKP4CW6L1x8cnoxHpjGrhiuLmDr8J92EgI9gpuNCZdjW70wzcGuk=s0-d-e1-ft#https://img.alicdn.com/tfs/TB1ciNPybj1gK0jSZFOXXc7GpXa-48-48.png\"\n"
                    + "                                                            alt=\"icon\"\n"
                    + "                                                            style=\"height: 29px; vertical-align:middle\"></span>\n"
                    + "                                                    <div style=\"display: inline-block; margin-left: 7px;\">Delivery\n"
                    + "                                                        details</div>\n"
                    + "                                                </td>\n"
                    + "                                            </tr>\n"
                    + "                                            <tr>\n"
                    + "                                                <td height=\"15\"></td>\n"
                    + "                                            </tr>\n"
                    + "                                            <tr>\n"
                    + "                                                <td>\n"
                    + "                                                    <table cellpadding=\"2\" cellspacing=\"0\" width=\"100%\">\n"
                    + "                                                        <tbody>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" valign=\"top\" style=\"font-weight:bold\">\n"
                    + "                                                                    Name:</td>\n"
                    + "                                                                <td width=\"75%\" valign=\"top\">" + orderDTO.getFullName() + "</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td style=\"font-weight:bold\">Address:</td>\n"
                    + "                                                                <td>" + orderDTO.getOrderAddress() + "</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td valign=\"top\" style=\"font-weight:bold\">Phone:\n"
                    + "                                                                </td>\n"
                    + "                                                                <td valign=\"top\">" + orderDTO.getPhoneNumber() + "</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                        </tbody>\n"
                    + "                                                    </table>\n"
                    + "                                                </td>\n"
                    + "                                            </tr>\n"
                    + "                                        </tbody>\n"
                    + "                                    </table>\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"30\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"margin-top: 30px;background:#f0f0f0;height: 9.6px\">\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                        </tbody>\n"
                    + "                    </table>\n"
                    + "                    <!-- END DELIVERY DETAILS -->\n"
                    + "                    <!-- START ORDER DETAIL -->\n"
                    + "                    <table lang=\"header\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\" style=\"width:100%\">\n"
                    + "                        <tbody>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"70\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"padding:30px;background:#ffffff;height:70px\">\n"
                    + "                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" height=\"70\" border=\"0\"\n"
                    + "                                        style=\"width:100%;height:70px\">\n"
                    + "                                        <tbody>\n"
                    + "                                            <tr>\n"
                    + "                                                <td\n"
                    + "                                                    style=\"font-family: 'Raleway', sans-serif; font-size:20px; color:#2a3a4b;\">\n"
                    + "                                                    <span><img\n"
                    + "                                                            src=\"https://ci6.googleusercontent.com/proxy/qpkhQlR635Jxtsrpbga7PYZv2NCz1935N6GJKvi4CK1fxYt7xAa4Yu9lDzjgJz6236kxpfXX0P-uyKCcHobCgHegZzVMjgQd3WfSiad2pcH1h58=s0-d-e1-ft#https://img.alicdn.com/tfs/TB1Y5JLyhn1gK0jSZKPXXXvUXXa-30-30.png\"\n"
                    + "                                                            alt=\"icon\"\n"
                    + "                                                            style=\"height: 29px; vertical-align:middle\"></span>\n"
                    + "                                                    <div style=\"display: inline-block; margin-left: 7px;\">Order summary\n"
                    + "                                                    </div>\n"
                    + "                                                </td>\n"
                    + "                                            </tr>\n"
                    + "                                            <tr>\n"
                    + "                                                <td height=\"15\"></td>\n"
                    + "                                            </tr>\n"
                    + "                                            <tr>\n"
                    + "                                                <td>\n"
                    + "                                                    <table cellpadding=\"2\" cellspacing=\"0\" width=\"100%\">\n"
                    + "                                                        <tbody>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Payment Method:</td>\n"
                    + "                                                                <td width=\"75%\" style=\"text-align: end;\">Cash On\n"
                    + "                                                                    Delivery</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Quantity:</td>\n"
                    + "                                                                <td width=\"75%\" style=\"text-align: end;\"> " + quantity + "</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Subtotal:</td>\n"
                    + "                                                                <td width=\"75%\" style=\"text-align: end;\"> " + subtotal + "₫</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Shipping fee:</td>\n"
                    + "                                                                <td width=\"75%\" style=\"text-align: end;\"> " + orderDTO.getShippingFee() + "₫</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td colspan=\"2\">\n"
                    + "                                                                    <hr>\n"
                    + "                                                                </td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Total:</td>\n"
                    + "                                                                <td width=\"75%\"\n"
                    + "                                                                    style=\"text-align: end; font-weight:bold; color:#499B14\">\n"
                    + "                                                                    " + total + "₫</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                        </tbody>\n"
                    + "                                                    </table>\n"
                    + "                                                </td>\n"
                    + "                                            </tr>\n"
                    + "                                        </tbody>\n"
                    + "                                    </table>\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"30\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"margin-top: 30px;background:#f0f0f0;height: 9.6px\">\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                        </tbody>\n"
                    + "                    </table>\n"
                    + "                    <!-- END ORDER DETAILS -->\n"
                    + "                </td>\n"
                    + "                <td bgcolor=\"#B3B3B3\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#D1D1D1\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#E8E8E8\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#F8F8F8\" width=\"1px\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#B3B3B3\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#D1D1D1\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#E8E8E8\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#F8F8F8\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "        </tbody>\n"
                    + "    </table>\n"
                    + "</div>",
                    "UTF-8", "html");
            //Gửi email
            Transport.send(msg);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean sendEmailOrderIsShippedSuccessfullyToGuest(OrderDTO orderDTO) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMPT HOST
        props.put("mail.smtp.port", "587"); //TLS 587 SSL465
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        //calculate total and subtotal
        double subtotal = 0;
        double total = 0;
        int quantity = 0;
        for (ProductDTO product : orderDTO.getProductsList()) {
            subtotal += product.getPrice() * product.getQuantity();
            quantity += product.getQuantity();
        }
        total = subtotal + orderDTO.getShippingFee();
        // Create authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM, PASSWORD);
            }
        };
        Session session = Session.getInstance(props, auth);
        MimeMessage msg = new MimeMessage(session);
        try {
            //Kiểu nội dung
            msg.addHeader("Content-type", "text/html; charset=UTF-8");
            //Ng gửi
            msg.setFrom(FROM);

            //Ng nhận
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(orderDTO.getEmail(), false));

            //Tiêu đề
            msg.setSubject("Your parcel was delivered successfully");

            //Quy định email nhận phản hồi
            //msg.setReplyTo()
            //Nội dung
            msg.setText("<div align=\"center\">\n"
                    + "    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:730px\">\n"
                    + "        <tbody>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#E8E8E8\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td bgcolor=\"#F8F8F8\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#E8E8E8\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#D1D1D1\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#FFF\">\n"
                    + "                    <table lang=\"header\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\" style=\"width:100%\">\n"
                    + "                        <tbody>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"70\" valign=\"middle\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"background:#ffffff;height:70\">\n"
                    + "                                    <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" height=\"70\" border=\"0\">\n"
                    + "                                        <tbody>\n"
                    + "                                          <td width=\"100px\"></td>"
                    + "                                            <td align=\"\"\n"
                    + "                                                style=\"font-family: 'Raleway', sans-serif; font-size:34px; color:#499B14; line-height:24px; font-weight: bold; letter-spacing: 1px;  padding-top: 30px;\">\n"
                    + "                                                <span><img\n"
                    + "                                                        src=\"https://lh3.googleusercontent.com/pw/AIL4fc-er2uf7hkMZyYkuvSFfPF7QX9cLbjBt1ACdCa4gc4P0yUicrt-4JvvRI9Rl07p4hbmUDiz6qjMLfINSxuRFJJ503WBWB4NCHgTcrCEN3fuSs4FvD-ueJUqI1agFyQF_1Qrsrh3knuzwbxpC7xSCuGzEpwolK9EkyQH2fJBmWxG_4MGqqb_dLvAIxaOvRnoyeybKKojoSa8PZRv3wPQFNhz0p2JZZpnHzbYyalIzzyClgrL4HX59ZBGEMKKTx4M-4xcPtXhBD-iBDen_xyzhqC_Ba_lXx2OJ3IYyGxeYgIkmBffxv6eJ01dwF54YoULwyajlagTRSQX85Ecv6KM0PQJXaMzhJoGvdA9CISDTxQcV6QTeePNNYH3qedqpKby5zwbc3jqU6J-9DTpeemo3PHw73KYkRH0GLnktP4cmQottwxw8HMwiwG7wBFNb9fbdgDvi1TCf5d1V-XQDY1pbY7eQUb2zqGcVuIi6U00SONfqTrnBtHWIojoDDmyNvJ5PIzLJuNHKD5uz84FHHjEfqcVf1TZV7RczBKwga5h2qat2zWHjy_bl3n3CqIWCOEwUoGRBk2RRXOLzk3KnNKKiv2kRHkXj8N-Ststw0aJZBIhpaUtU8BFjC1-MMsB72b-xYPiYCZ1DImSjT7DDU9DynyhUyoiv1JWKBAORtKV2WWZJk4f87ukwUwSNWUImJjwWy7-fkUEsNpDqxjC8HVNlCCtLce1w7wqLONq24OhMT4TwsGGNkT7Ell8p4zeVYSEOmdRyhiEjmQRZSPTB0b_-Sj2TPCBFXL8FwbiVmQFaixa2hB62bdNamsI0ZEuoRID6dd-QpyNv0tJU9e9UaN2Te199q5qx565y0xrJ5Xgwl6Mt4Y9BteFmgSsgyaHsmvatKJObzuQ2Mqu4RBiiQZgnXA3dLG6LJTivalH6kaGTQVc5a9B7I8-Io__SZk=s250-k-rw-no\"\n"
                    + "                                                        alt=\"icon\"\n"
                    + "                                                        style=\"width:100%;max-width:79px;vertical-align:middle\"></span>\n"
                    + "                                                Bird Meal Order Shop\n"
                    + "                                            </td>\n" 
                    + "                                        </tbody>\n"
                    + "                                    </table>\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td height=\"18\"></td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td align=\"center\"\n"
                    + "                                    style=\"font-family: 'Raleway', sans-serif; font-size:22px; font-weight: bold; color:#2a3a4b;\">\n"
                    + "                                    Your parcel was delivered successfully.</td>\n"
                    + "                            </tr>\n"
                    + "                        </tbody>\n"
                    + "                    </table>\n"
                    + "                    <table lang=\"header\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\" style=\"width:100%\">\n"
                    + "                        <tbody>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"70\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"padding:20px 30px 30px 30px;background:#ffffff;height:70px\">\n"
                    + "                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" height=\"70\" border=\"0\"\n"
                    + "                                        style=\"width:100%;height:70px\">\n"
                    + "                                        <tbody>\n"
                    + "                                            <tr>\n"
                    + "                                                <td>\n"
                    + "                                                    <h3>Hello " + orderDTO.getFullName() + ",</h3>\n"
                    + "                                                    <p>We are grateful that you place the order #" + orderDTO.getOrderID() + " in our shop. Hope to serve you again and receive your feedback on our website. Your feedback will make us improve our services.</p>\n"
                    + "                                                </td>\n"
                    + "                                        </tbody>\n"
                    + "                                    </table>\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"30\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"margin-top: 30px;background:#f0f0f0;height: 9.6px\">\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                        </tbody>\n"
                    + "                    </table>\n"
                    + "                    <!-- START DELIVERY DETAILS -->\n"
                    + "                    <table lang=\"header\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\" style=\"width:100%\">\n"
                    + "                        <tbody>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"70\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"padding:30px;background:#ffffff;height:70px\">\n"
                    + "                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" height=\"70\" border=\"0\"\n"
                    + "                                        style=\"width:100%;height:70px\">\n"
                    + "                                        <tbody>\n"
                    + "                                            <tr>\n"
                    + "                                                <td\n"
                    + "                                                    style=\"font-family: 'Raleway', sans-serif; font-size:20px; color:#2a3a4b;\">\n"
                    + "                                                    <span><img\n"
                    + "                                                            src=\"https://ci6.googleusercontent.com/proxy/AUmakQI121QQ_WvdspszI-42jmht-u29A4iIvgfX5V-q3Z9qyUAfpUFNiqpGKP4CW6L1x8cnoxHpjGrhiuLmDr8J92EgI9gpuNCZdjW70wzcGuk=s0-d-e1-ft#https://img.alicdn.com/tfs/TB1ciNPybj1gK0jSZFOXXc7GpXa-48-48.png\"\n"
                    + "                                                            alt=\"icon\"\n"
                    + "                                                            style=\"height: 29px; vertical-align:middle\"></span>\n"
                    + "                                                    <div style=\"display: inline-block; margin-left: 7px;\">Delivery\n"
                    + "                                                        details</div>\n"
                    + "                                                </td>\n"
                    + "                                            </tr>\n"
                    + "                                            <tr>\n"
                    + "                                                <td height=\"15\"></td>\n"
                    + "                                            </tr>\n"
                    + "                                            <tr>\n"
                    + "                                                <td>\n"
                    + "                                                    <table cellpadding=\"2\" cellspacing=\"0\" width=\"100%\">\n"
                    + "                                                        <tbody>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" valign=\"top\" style=\"font-weight:bold\">\n"
                    + "                                                                    Name:</td>\n"
                    + "                                                                <td width=\"75%\" valign=\"top\">" + orderDTO.getFullName() + "</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td style=\"font-weight:bold\">Address:</td>\n"
                    + "                                                                <td>" + orderDTO.getOrderAddress() + "</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td valign=\"top\" style=\"font-weight:bold\">Phone:\n"
                    + "                                                                </td>\n"
                    + "                                                                <td valign=\"top\">" + orderDTO.getPhoneNumber() + "</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                        </tbody>\n"
                    + "                                                    </table>\n"
                    + "                                                </td>\n"
                    + "                                            </tr>\n"
                    + "                                        </tbody>\n"
                    + "                                    </table>\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"30\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"margin-top: 30px;background:#f0f0f0;height: 9.6px\">\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                        </tbody>\n"
                    + "                    </table>\n"
                    + "                    <!-- END DELIVERY DETAILS -->\n"
                    + "                    <!-- START ORDER DETAIL -->\n"
                    + "                    <table lang=\"header\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\" style=\"width:100%\">\n"
                    + "                        <tbody>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"70\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"padding:30px;background:#ffffff;height:70px\">\n"
                    + "                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" height=\"70\" border=\"0\"\n"
                    + "                                        style=\"width:100%;height:70px\">\n"
                    + "                                        <tbody>\n"
                    + "                                            <tr>\n"
                    + "                                                <td\n"
                    + "                                                    style=\"font-family: 'Raleway', sans-serif; font-size:20px; color:#2a3a4b;\">\n"
                    + "                                                    <span><img\n"
                    + "                                                            src=\"https://ci6.googleusercontent.com/proxy/qpkhQlR635Jxtsrpbga7PYZv2NCz1935N6GJKvi4CK1fxYt7xAa4Yu9lDzjgJz6236kxpfXX0P-uyKCcHobCgHegZzVMjgQd3WfSiad2pcH1h58=s0-d-e1-ft#https://img.alicdn.com/tfs/TB1Y5JLyhn1gK0jSZKPXXXvUXXa-30-30.png\"\n"
                    + "                                                            alt=\"icon\"\n"
                    + "                                                            style=\"height: 29px; vertical-align:middle\"></span>\n"
                    + "                                                    <div style=\"display: inline-block; margin-left: 7px;\">Order summary\n"
                    + "                                                    </div>\n"
                    + "                                                </td>\n"
                    + "                                            </tr>\n"
                    + "                                            <tr>\n"
                    + "                                                <td height=\"15\"></td>\n"
                    + "                                            </tr>\n"
                    + "                                            <tr>\n"
                    + "                                                <td>\n"
                    + "                                                    <table cellpadding=\"2\" cellspacing=\"0\" width=\"100%\">\n"
                    + "                                                        <tbody>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Payment Method:</td>\n"
                    + "                                                                <td width=\"75%\" style=\"text-align: end;\">Cash On\n"
                    + "                                                                    Delivery</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Quantity:</td>\n"
                    + "                                                                <td width=\"75%\" style=\"text-align: end;\"> " + quantity + "</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Subtotal:</td>\n"
                    + "                                                                <td width=\"75%\" style=\"text-align: end;\"> " + subtotal + "₫</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Shipping fee:</td>\n"
                    + "                                                                <td width=\"75%\" style=\"text-align: end;\"> " + orderDTO.getShippingFee() + "₫</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td colspan=\"2\">\n"
                    + "                                                                    <hr>\n"
                    + "                                                                </td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Total:</td>\n"
                    + "                                                                <td width=\"75%\"\n"
                    + "                                                                    style=\"text-align: end; font-weight:bold; color:#499B14\">\n"
                    + "                                                                    " + total + "₫</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                        </tbody>\n"
                    + "                                                    </table>\n"
                    + "                                                </td>\n"
                    + "                                            </tr>\n"
                    + "                                        </tbody>\n"
                    + "                                    </table>\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"30\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"margin-top: 30px;background:#f0f0f0;height: 9.6px\">\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                        </tbody>\n"
                    + "                    </table>\n"
                    + "                    <!-- END ORDER DETAILS -->\n"
                    + "                </td>\n"
                    + "                <td bgcolor=\"#B3B3B3\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#D1D1D1\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#E8E8E8\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#F8F8F8\" width=\"1px\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#B3B3B3\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#D1D1D1\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#E8E8E8\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#F8F8F8\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "        </tbody>\n"
                    + "    </table>\n"
                    + "</div>",
                    "UTF-8", "html");
            //Gửi email
            Transport.send(msg);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean sendEmailOrderIsCancelledToGuest(OrderDTO orderDTO) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMPT HOST
        props.put("mail.smtp.port", "587"); //TLS 587 SSL465
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        //calculate total and subtotal
        double subtotal = 0;
        double total = 0;
        int quantity = 0;
        for (ProductDTO product : orderDTO.getProductsList()) {
            subtotal += product.getPrice() * product.getQuantity();
            quantity += product.getQuantity();
        }
        total = subtotal + orderDTO.getShippingFee();
        // Create authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM, PASSWORD);
            }
        };
        Session session = Session.getInstance(props, auth);
        MimeMessage msg = new MimeMessage(session);
        try {
            //Kiểu nội dung
            msg.addHeader("Content-type", "text/html; charset=UTF-8");
            //Ng gửi
            msg.setFrom(FROM);

            //Ng nhận
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(orderDTO.getEmail(), false));

            //Tiêu đề
            msg.setSubject("Your parcel has been cancelled");

            //Quy định email nhận phản hồi
            //msg.setReplyTo()
            //Nội dung
            msg.setText("<div align=\"center\">\n"
                    + "    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:730px\">\n"
                    + "        <tbody>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#E8E8E8\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td bgcolor=\"#F8F8F8\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#E8E8E8\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#D1D1D1\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#FFF\">\n"
                    + "                    <table lang=\"header\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\" style=\"width:100%\">\n"
                    + "                        <tbody>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"70\" valign=\"middle\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"background:#ffffff;height:70\">\n"
                    + "                                    <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" height=\"70\" border=\"0\">\n"
                    + "                                        <tbody>\n"
                    + "                                          <td width=\"100px\"></td>"
                    + "                                            <td align=\"\"\n"
                    + "                                                style=\"font-family: 'Raleway', sans-serif; font-size:34px; color:#499B14; line-height:24px; font-weight: bold; letter-spacing: 1px;  padding-top: 30px;\">\n"
                    + "                                                <span><img\n"
                    + "                                                        src=\"https://lh3.googleusercontent.com/pw/AIL4fc-er2uf7hkMZyYkuvSFfPF7QX9cLbjBt1ACdCa4gc4P0yUicrt-4JvvRI9Rl07p4hbmUDiz6qjMLfINSxuRFJJ503WBWB4NCHgTcrCEN3fuSs4FvD-ueJUqI1agFyQF_1Qrsrh3knuzwbxpC7xSCuGzEpwolK9EkyQH2fJBmWxG_4MGqqb_dLvAIxaOvRnoyeybKKojoSa8PZRv3wPQFNhz0p2JZZpnHzbYyalIzzyClgrL4HX59ZBGEMKKTx4M-4xcPtXhBD-iBDen_xyzhqC_Ba_lXx2OJ3IYyGxeYgIkmBffxv6eJ01dwF54YoULwyajlagTRSQX85Ecv6KM0PQJXaMzhJoGvdA9CISDTxQcV6QTeePNNYH3qedqpKby5zwbc3jqU6J-9DTpeemo3PHw73KYkRH0GLnktP4cmQottwxw8HMwiwG7wBFNb9fbdgDvi1TCf5d1V-XQDY1pbY7eQUb2zqGcVuIi6U00SONfqTrnBtHWIojoDDmyNvJ5PIzLJuNHKD5uz84FHHjEfqcVf1TZV7RczBKwga5h2qat2zWHjy_bl3n3CqIWCOEwUoGRBk2RRXOLzk3KnNKKiv2kRHkXj8N-Ststw0aJZBIhpaUtU8BFjC1-MMsB72b-xYPiYCZ1DImSjT7DDU9DynyhUyoiv1JWKBAORtKV2WWZJk4f87ukwUwSNWUImJjwWy7-fkUEsNpDqxjC8HVNlCCtLce1w7wqLONq24OhMT4TwsGGNkT7Ell8p4zeVYSEOmdRyhiEjmQRZSPTB0b_-Sj2TPCBFXL8FwbiVmQFaixa2hB62bdNamsI0ZEuoRID6dd-QpyNv0tJU9e9UaN2Te199q5qx565y0xrJ5Xgwl6Mt4Y9BteFmgSsgyaHsmvatKJObzuQ2Mqu4RBiiQZgnXA3dLG6LJTivalH6kaGTQVc5a9B7I8-Io__SZk=s250-k-rw-no\"\n"
                    + "                                                        alt=\"icon\"\n"
                    + "                                                        style=\"width:100%;max-width:79px;vertical-align:middle\"></span>\n"
                    + "                                                Bird Meal Order Shop\n"
                    + "                                            </td>\n" 
                    + "                                        </tbody>\n"
                    + "                                    </table>\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td height=\"18\"></td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td align=\"center\"\n"
                    + "                                    style=\"font-family: 'Raleway', sans-serif; font-size:22px; font-weight: bold; color:#2a3a4b;\">\n"
                    + "                                    Your parcel has been cancelled.</td>\n"
                    + "                            </tr>\n"
                    + "                        </tbody>\n"
                    + "                    </table>\n"
                    + "                    <table lang=\"header\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\" style=\"width:100%\">\n"
                    + "                        <tbody>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"70\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"padding:20px 30px 30px 30px;background:#ffffff;height:70px\">\n"
                    + "                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" height=\"70\" border=\"0\"\n"
                    + "                                        style=\"width:100%;height:70px\">\n"
                    + "                                        <tbody>\n"
                    + "                                            <tr>\n"
                    + "                                                <td>\n"
                    + "                                                    <h3>Hello " + orderDTO.getFullName() + ",</h3>\n"
                    + "                                                    <p>We are regret that your order #" + orderDTO.getOrderID() + " has been cancelled.</p>\n"
                    + "                                                </td>\n"
                    + "                                        </tbody>\n"
                    + "                                    </table>\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"30\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"margin-top: 30px;background:#f0f0f0;height: 9.6px\">\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                        </tbody>\n"
                    + "                    </table>\n"
                    + "                    <!-- START DELIVERY DETAILS -->\n"
                    + "                    <table lang=\"header\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\" style=\"width:100%\">\n"
                    + "                        <tbody>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"70\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"padding:30px;background:#ffffff;height:70px\">\n"
                    + "                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" height=\"70\" border=\"0\"\n"
                    + "                                        style=\"width:100%;height:70px\">\n"
                    + "                                        <tbody>\n"
                    + "                                            <tr>\n"
                    + "                                                <td\n"
                    + "                                                    style=\"font-family: 'Raleway', sans-serif; font-size:20px; color:#2a3a4b;\">\n"
                    + "                                                    <span><img\n"
                    + "                                                            src=\"https://ci6.googleusercontent.com/proxy/AUmakQI121QQ_WvdspszI-42jmht-u29A4iIvgfX5V-q3Z9qyUAfpUFNiqpGKP4CW6L1x8cnoxHpjGrhiuLmDr8J92EgI9gpuNCZdjW70wzcGuk=s0-d-e1-ft#https://img.alicdn.com/tfs/TB1ciNPybj1gK0jSZFOXXc7GpXa-48-48.png\"\n"
                    + "                                                            alt=\"icon\"\n"
                    + "                                                            style=\"height: 29px; vertical-align:middle\"></span>\n"
                    + "                                                    <div style=\"display: inline-block; margin-left: 7px;\">Delivery\n"
                    + "                                                        details</div>\n"
                    + "                                                </td>\n"
                    + "                                            </tr>\n"
                    + "                                            <tr>\n"
                    + "                                                <td height=\"15\"></td>\n"
                    + "                                            </tr>\n"
                    + "                                            <tr>\n"
                    + "                                                <td>\n"
                    + "                                                    <table cellpadding=\"2\" cellspacing=\"0\" width=\"100%\">\n"
                    + "                                                        <tbody>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" valign=\"top\" style=\"font-weight:bold\">\n"
                    + "                                                                    Name:</td>\n"
                    + "                                                                <td width=\"75%\" valign=\"top\">" + orderDTO.getFullName() + "</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td style=\"font-weight:bold\">Address:</td>\n"
                    + "                                                                <td>" + orderDTO.getOrderAddress() + "</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td valign=\"top\" style=\"font-weight:bold\">Phone:\n"
                    + "                                                                </td>\n"
                    + "                                                                <td valign=\"top\">" + orderDTO.getPhoneNumber() + "</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                        </tbody>\n"
                    + "                                                    </table>\n"
                    + "                                                </td>\n"
                    + "                                            </tr>\n"
                    + "                                        </tbody>\n"
                    + "                                    </table>\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"30\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"margin-top: 30px;background:#f0f0f0;height: 9.6px\">\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                        </tbody>\n"
                    + "                    </table>\n"
                    + "                    <!-- END DELIVERY DETAILS -->\n"
                    + "                    <!-- START ORDER DETAIL -->\n"
                    + "                    <table lang=\"header\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\" style=\"width:100%\">\n"
                    + "                        <tbody>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"70\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"padding:30px;background:#ffffff;height:70px\">\n"
                    + "                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" height=\"70\" border=\"0\"\n"
                    + "                                        style=\"width:100%;height:70px\">\n"
                    + "                                        <tbody>\n"
                    + "                                            <tr>\n"
                    + "                                                <td\n"
                    + "                                                    style=\"font-family: 'Raleway', sans-serif; font-size:20px; color:#2a3a4b;\">\n"
                    + "                                                    <span><img\n"
                    + "                                                            src=\"https://ci6.googleusercontent.com/proxy/qpkhQlR635Jxtsrpbga7PYZv2NCz1935N6GJKvi4CK1fxYt7xAa4Yu9lDzjgJz6236kxpfXX0P-uyKCcHobCgHegZzVMjgQd3WfSiad2pcH1h58=s0-d-e1-ft#https://img.alicdn.com/tfs/TB1Y5JLyhn1gK0jSZKPXXXvUXXa-30-30.png\"\n"
                    + "                                                            alt=\"icon\"\n"
                    + "                                                            style=\"height: 29px; vertical-align:middle\"></span>\n"
                    + "                                                    <div style=\"display: inline-block; margin-left: 7px;\">Order summary\n"
                    + "                                                    </div>\n"
                    + "                                                </td>\n"
                    + "                                            </tr>\n"
                    + "                                            <tr>\n"
                    + "                                                <td height=\"15\"></td>\n"
                    + "                                            </tr>\n"
                    + "                                            <tr>\n"
                    + "                                                <td>\n"
                    + "                                                    <table cellpadding=\"2\" cellspacing=\"0\" width=\"100%\">\n"
                    + "                                                        <tbody>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Payment Method:</td>\n"
                    + "                                                                <td width=\"75%\" style=\"text-align: end;\">Cash On\n"
                    + "                                                                    Delivery</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Quantity:</td>\n"
                    + "                                                                <td width=\"75%\" style=\"text-align: end;\"> " + quantity + "</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Subtotal:</td>\n"
                    + "                                                                <td width=\"75%\" style=\"text-align: end;\"> " + subtotal + "₫</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Shipping fee:</td>\n"
                    + "                                                                <td width=\"75%\" style=\"text-align: end;\"> " + orderDTO.getShippingFee() + "₫</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td colspan=\"2\">\n"
                    + "                                                                    <hr>\n"
                    + "                                                                </td>\n"
                    + "                                                            </tr>\n"
                    + "                                                            <tr>\n"
                    + "                                                                <td width=\"25%\" style=\"font-weight:bold\">\n"
                    + "                                                                    Total:</td>\n"
                    + "                                                                <td width=\"75%\"\n"
                    + "                                                                    style=\"text-align: end; font-weight:bold; color:#499B14\">\n"
                    + "                                                                    " + total + "₫</td>\n"
                    + "                                                            </tr>\n"
                    + "                                                        </tbody>\n"
                    + "                                                    </table>\n"
                    + "                                                </td>\n"
                    + "                                            </tr>\n"
                    + "                                        </tbody>\n"
                    + "                                    </table>\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td width=\"100%\" height=\"30\" valign=\"top\" bgcolor=\"#FFFFFF\"\n"
                    + "                                    style=\"margin-top: 30px;background:#f0f0f0;height: 9.6px\">\n"
                    + "                                </td>\n"
                    + "                            </tr>\n"
                    + "                        </tbody>\n"
                    + "                    </table>\n"
                    + "                    <!-- END ORDER DETAILS -->\n"
                    + "                </td>\n"
                    + "                <td bgcolor=\"#B3B3B3\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#D1D1D1\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#E8E8E8\" width=\"1px\"></td>\n"
                    + "                <td bgcolor=\"#F8F8F8\" width=\"1px\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#B3B3B3\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#D1D1D1\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#E8E8E8\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td colspan=\"2\"></td>\n"
                    + "                <td bgcolor=\"#F8F8F8\" colspan=\"3\" height=\"1px\"></td>\n"
                    + "                <td colspan=\"3\"></td>\n"
                    + "            </tr>\n"
                    + "        </tbody>\n"
                    + "    </table>\n"
                    + "</div>",
                    "UTF-8", "html");
            //Gửi email
            Transport.send(msg);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
