import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class bot extends TelegramLongPollingBot {

    String BotToken = "937427272:AAGF-8SYee2GtDoXiK8eh7GMHRaF5u7Jvqc";
    String BotName = "SadMealBot";
    boolean isOver = false;


    public String moveHuman = " ";
    public String moveAI = " ";


    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
       /* ReplyKeyboardMarkup MarkUp = new ReplyKeyboardMarkup();
        MarkUp.setSelective(true);
        MarkUp.setOneTimeKeyboard(true);
        MarkUp.setResizeKeyboard(true);*/

        if (message != null && message.hasText()) {
            switch (message.getText()) {
                case "/start":
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.enableMarkdown(true);
                    sendMessage.setChatId(message.getChatId());
                    sendMessage.setReplyToMessageId(message.getMessageId());
                    sendMessage.setText("Hello from the other side");
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;

                case "/lets_play":
                    SendMessage sendMessage1 = new SendMessage();
                    sendMessage1.enableMarkdown(true);
                    sendMessage1.setChatId(message.getChatId());
                    sendMessage1.setReplyToMessageId(message.getMessageId());
                    sendMessage1.setText("Which game do you want to play?");

                    InlineKeyboardMarkup button = new InlineKeyboardMarkup();
                    InlineKeyboardButton inbutton = new InlineKeyboardButton();

                    List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
                    keyboardButtonsRow1.add(new InlineKeyboardButton().setText("Tic Tac Toe").setCallbackData("Tic Tac Toe"));

                    List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
                    keyboardButtonsRow2.add(new InlineKeyboardButton().setText("Bugs and bears").setCallbackData("Hello2"));

                    List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
                    keyboardButtonsRow3.add(new InlineKeyboardButton().setText("Third game").setCallbackData("Hello3"));

                    List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
                    rowList.add(keyboardButtonsRow1);
                    rowList.add(keyboardButtonsRow2);
                    rowList.add(keyboardButtonsRow3);

                    button.setKeyboard(rowList);
                    sendMessage1.setReplyMarkup(button);

                    try {
                        execute(sendMessage1);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;

                default:
            }
        } else if (update.hasCallbackQuery()) {

            if (update.getCallbackQuery().getData().equals("Tic Tac Toe")) {

                SendMessage sendMessage1 = new SendMessage();
                InlineKeyboardMarkup button = new InlineKeyboardMarkup();
                EditMessageReplyMarkup changing = new EditMessageReplyMarkup();
                Message message1 = update.getMessage();
                update.getCallbackQuery();
                Message msg = update.getCallbackQuery().getMessage();
                changing.setChatId(msg.getChatId());
                changing.setMessageId(msg.getMessageId());
                changing.setInlineMessageId(update.getCallbackQuery().getInlineMessageId());
                changing.setReplyMarkup(button);

                List<InlineKeyboardButton> keyboardButtons1 = new ArrayList<>();
                keyboardButtons1.add(new InlineKeyboardButton().setText("X").setCallbackData("X"));

                List<InlineKeyboardButton> keyboardButtons2 = new ArrayList<>();
                keyboardButtons2.add(new InlineKeyboardButton().setText("O").setCallbackData("O"));

                List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
                rowList.add(keyboardButtons1);
                rowList.add(keyboardButtons2);
                button.setKeyboard(rowList);
                sendMessage1.setReplyMarkup(button);

                EditMessageText edittext = new EditMessageText();
                edittext.setChatId(msg.getChatId());
                edittext.setMessageId(msg.getMessageId());
                edittext.setReplyMarkup(button);
                edittext.setText("Which one do you want to be? X or O ?");

                try {
                    execute(edittext);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            }

            else if (update.getCallbackQuery().getData().equals("X")) {

                moveHuman = "X";
                moveAI = "O";

                SendMessage sendMessage1 = new SendMessage();
                InlineKeyboardMarkup button = new InlineKeyboardMarkup();
                EditMessageReplyMarkup changing = new EditMessageReplyMarkup();
                Message message1 = update.getMessage();
                update.getCallbackQuery();
                Message msg = update.getCallbackQuery().getMessage();
                changing.setChatId(msg.getChatId());
                changing.setMessageId(msg.getMessageId());
                changing.setInlineMessageId(update.getCallbackQuery().getInlineMessageId());
                changing.setReplyMarkup(button);
                List<List<InlineKeyboardButton>> rowList = makeInlineButtonsForGame(update.getCallbackQuery().getData(), moveHuman, false);

                button.setKeyboard(rowList);

                sendMessage1.setReplyMarkup(button);

                EditMessageText edittext = new EditMessageText();
                edittext.setChatId(msg.getChatId());
                edittext.setMessageId(msg.getMessageId());
                edittext.setReplyMarkup(button);
                edittext.setText("You are " + moveHuman + ". Go ahead. Luck.");

                try {
                    execute(edittext);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else if (update.getCallbackQuery().getData().equals("O")) {

                moveHuman = "O";
                moveAI = "X";
                int r = random();


                SendMessage sendMessage1 = new SendMessage();
                InlineKeyboardMarkup button = new InlineKeyboardMarkup();
                EditMessageReplyMarkup changing = new EditMessageReplyMarkup();
                Message message1 = update.getMessage();
                update.getCallbackQuery();
                Message msg = update.getCallbackQuery().getMessage();
                changing.setChatId(msg.getChatId());
                changing.setMessageId(msg.getMessageId());
                changing.setInlineMessageId(update.getCallbackQuery().getInlineMessageId());
                changing.setReplyMarkup(button);

                List<List<InlineKeyboardButton>> rowList = makeInlineButtonsForGame(update.getCallbackQuery().getData(), moveHuman, false);
                button.setKeyboard(rowList);
                sendMessage1.setReplyMarkup(button);

                EditMessageText edittext = new EditMessageText();
                edittext.setChatId(msg.getChatId());
                edittext.setMessageId(msg.getMessageId());
                edittext.setReplyMarkup(button);
                edittext.setText("You -- " + moveHuman + ". \nAI -- " + moveAI + ".");

                String mes = "";
                for (int i = 0; i < 9; i++) {
                    if (r == i) mes = mes + i;

                }


                try {
                    execute(edittext);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }


            else if (update.getCallbackQuery().getData().charAt(1) == '_') {
                String call = update.getCallbackQuery().getData();

                if (call.split("_")[1].equals("0")) {
                    System.out.println("NO reponse");
                    return;
                }

                SendMessage sendMessage1 = new SendMessage();
                InlineKeyboardMarkup button = new InlineKeyboardMarkup();
                EditMessageReplyMarkup changing = new EditMessageReplyMarkup();
                Message message1 = update.getMessage();
                update.getCallbackQuery();
                Message msg = update.getCallbackQuery().getMessage();
                changing.setChatId(msg.getChatId());
                changing.setMessageId(msg.getMessageId());
                changing.setInlineMessageId(update.getCallbackQuery().getInlineMessageId());
                changing.setReplyMarkup(button);

                List<List<InlineKeyboardButton>> rowList = makeInlineButtonsForGame(call, moveHuman, false);
                String turnsHistory = makeCallBackData(call, moveHuman, "0")[1];

                String newCallData = makeCallBackData(call, moveHuman, "0")[0];
                String turnsHistoryOnlyZeros = turnsHistory.replace("X", "").replace("O", "");
                int max_length = turnsHistoryOnlyZeros.length();
                if (max_length > 0){
                    Random rn = new Random();
                    int ai_move = rn.nextInt(max_length);
                    String buttonChosenByAI = turnsHistoryOnlyZeros.charAt(ai_move)+"";
                    rowList = makeInlineButtonsForGame(buttonChosenByAI + "_" + turnsHistory + "_" + moveAI, moveAI, false);
                    turnsHistory = makeCallBackData(newCallData, moveAI, "0")[1];
                }

//                sendMessage1.setReplyMarkup(button);

                EditMessageText edittext = new EditMessageText();
                edittext.setChatId(msg.getChatId());
                edittext.setMessageId(msg.getMessageId());
                edittext.setReplyMarkup(button);

                if (isOver){
                    makeCallBackData(call, "0", "0");
                    rowList = makeInlineButtonsForGame(call, "0", true);
                    button.setKeyboard(rowList);
                } else {
                    button.setKeyboard(rowList);
                }

                if (WinningStatement(turnsHistory, moveHuman) == 1){
                    System.out.println("HUMAN WON");
                    edittext.setText("You -- " + moveHuman + ". \nAI -- " + moveAI + ".\n---------------------------------" + "\nHUMAN WON!");
                    isOver = true;

                } else if (WinningStatement(turnsHistory, moveAI) == 1){
                    System.out.println("AI WON");
                    edittext.setText("You -- " + moveHuman + ". \nAI -- " + moveAI + ".\n---------------------------------" + "AI WON!");
                    isOver = true;
                } else {
                    edittext.setText("You -- " + moveHuman + ". \nAI -- " + moveAI + ".\n---------------------------------");
                }



                sendMessage1.setReplyMarkup(button);

                try {
                    execute(edittext);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getBotUsername() {
        return BotName;
    }

    public String getBotToken() {
        return BotToken;
    }

    public int WinningStatement(String turnsHistory, String player) {

        // X wins = 1    O wins = 2  nobody wins = 0

        String game = turnsHistory;
        System.out.println("CHecking the history: " + game);
        int state = 0;
        try {


            if (check(game, 0, player) && check(game, 3, player) && check(game, 6, player)) state = 1;
            if (check(game, 1, player) && check(game, 4, player) && check(game, 7, player)) state = 1;
            if (check(game, 2, player) && check(game, 5, player) && check(game, 8, player)) state = 1;
            if (check(game, 0, player) && check(game, 1, player) && check(game, 2, player)) state = 1;
            if (check(game, 3, player) && check(game, 4, player) && check(game, 5, player)) state = 1;
            if (check(game, 6, player) && check(game, 7, player) && check(game, 8, player)) state = 1;
            if (check(game, 0, player) && check(game, 4, player) && check(game, 8, player)) state = 1;
            if (check(game, 2, player) && check(game, 4, player) && check(game, 6, player)) state = 1;

//            if(player.equals("X")) return 1;
//            else if(player.equals("O")) return 2;
            return state;

        } catch (Exception e) {
            e.printStackTrace();
            return state;
        }

    }

    public EditMessageText EditingMode(Update update, int n, int m, String XorO, String callbackdata) {

        SendMessage sendMessage1 = new SendMessage();
        InlineKeyboardMarkup button = new InlineKeyboardMarkup();
        EditMessageReplyMarkup changing = new EditMessageReplyMarkup();
        Message message1 = update.getMessage();
        update.getCallbackQuery();
        Message msg = update.getCallbackQuery().getMessage();
        changing.setChatId(msg.getChatId());
        changing.setMessageId(msg.getMessageId());
        changing.setInlineMessageId(update.getCallbackQuery().getInlineMessageId());
        changing.setReplyMarkup(button);


        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();

        if (n == 0 && m == 0)
            keyboardButtonsRow1.add(new InlineKeyboardButton().setText(XorO).setCallbackData(callbackdata));
        else keyboardButtonsRow1.add(new InlineKeyboardButton().setText(" ").setCallbackData("1"));

        if (n == 0 && m == 1)
            keyboardButtonsRow1.add(new InlineKeyboardButton().setText(XorO).setCallbackData(callbackdata));
        else keyboardButtonsRow1.add(new InlineKeyboardButton().setText(" ").setCallbackData("2"));

        if (n == 0 && m == 2)
            keyboardButtonsRow1.add(new InlineKeyboardButton().setText(XorO).setCallbackData(callbackdata));
        else keyboardButtonsRow1.add(new InlineKeyboardButton().setText(" ").setCallbackData("3"));


        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();

        if (n == 1 && m == 0)
            keyboardButtonsRow2.add(new InlineKeyboardButton().setText(XorO).setCallbackData(callbackdata));
        else keyboardButtonsRow2.add(new InlineKeyboardButton().setText(" ").setCallbackData("4"));

        if (n == 1 && m == 0)
            keyboardButtonsRow2.add(new InlineKeyboardButton().setText(XorO).setCallbackData(callbackdata));
        else keyboardButtonsRow2.add(new InlineKeyboardButton().setText(" ").setCallbackData("5"));

        if (n == 1 && m == 0)
            keyboardButtonsRow2.add(new InlineKeyboardButton().setText(XorO).setCallbackData(callbackdata));
        else keyboardButtonsRow2.add(new InlineKeyboardButton().setText(" ").setCallbackData("6"));

        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
        if (n == 1 && m == 0)
            keyboardButtonsRow3.add(new InlineKeyboardButton().setText(XorO).setCallbackData(callbackdata));
        else keyboardButtonsRow3.add(new InlineKeyboardButton().setText(" ").setCallbackData("7"));

        if (n == 1 && m == 0)
            keyboardButtonsRow3.add(new InlineKeyboardButton().setText(XorO).setCallbackData(callbackdata));
        else keyboardButtonsRow3.add(new InlineKeyboardButton().setText(" ").setCallbackData("8"));

        if (n == 1 && m == 0)
            keyboardButtonsRow3.add(new InlineKeyboardButton().setText(XorO).setCallbackData(callbackdata));
        else keyboardButtonsRow3.add(new InlineKeyboardButton().setText(" ").setCallbackData("9"));

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        rowList.add(keyboardButtonsRow3);

        button.setKeyboard(rowList);

        sendMessage1.setReplyMarkup(button);

        EditMessageText edittext = new EditMessageText();
        edittext.setChatId(msg.getChatId());
        edittext.setMessageId(msg.getMessageId());
        edittext.setReplyMarkup(button);
        edittext.setText("here we go");

        return edittext;

    }

    public int mimimax(int matrix[]) {
        return 0;
    }

    public String changer(String chatid, int order, String XorO) {

        return null;
    }

    public String read(String FileName) {

        Scanner fr = null;
        String ls = "";

        try {
            fr = new Scanner(new File(FileName + ".txt"));
            while (fr.hasNextLine()) ls = fr.nextLine();
            fr.close();
            return ls;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Boolean write(String FileName, String message) {

        try {
            FileWriter fw = new FileWriter(FileName + ".txt");
            fw.write(message);
            fw.close();
            return true;


        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int random() {
        Random num = new Random();
        return num.nextInt(9);
    }

    public String saving(String FileName) {
        Scanner sv = null;
        String game = "";

        return null;
    }

    public boolean check(String text, Integer index, String compare) {
        System.out.println("CHecking: " + text + " index=" + index + " compare=" + compare);
        if (text.length() < index){
            return false;
        }
        if ((text.charAt(index) + "").equals(compare)){
            System.out.println("CHecked TRUE");
            return true;
        }
        System.out.println("CHecked FALSE");
        return false;
    }

    public String[] makeCallBackData(String currentCallBackData, String move, String button) {

        String buttonNumber = "";
        String turnsHistory = "";
        String status = "";

        String[] result = new String[3];

        if (currentCallBackData.equals("X") || currentCallBackData.equals("O")){
            buttonNumber = button;
            turnsHistory = "012345678";
            status = "0";
            System.out.println("FIRST BUTTON " + button);
            result[0] = button + "_" + turnsHistory + "_" + status;
            result[1] = turnsHistory;
            result[2] = " ";

            System.out.println(result[0]);
            return result;
        } else {
            buttonNumber = currentCallBackData.split("_")[0];
            turnsHistory = currentCallBackData.split("_")[1];
            status = currentCallBackData.split("_")[2];
        }

        String buttonText = "";

        turnsHistory = turnsHistory.replace(turnsHistory.charAt(Integer.parseInt(buttonNumber)) + "", move);
        System.out.println(turnsHistory);

        char currentButtonStatus = turnsHistory.charAt(Integer.parseInt(button));
        if (currentButtonStatus != 'X' && currentButtonStatus != 'O') {
            buttonText = " ";
        } else {
            buttonText = currentButtonStatus + "";
        }

        if (button.equals(buttonNumber)) {
            result[0] = button + "_" + turnsHistory + "_" + move;
        }


        if(move.equals("0")){
            result[0] = button + "_" + 0 + "_" + status;
            result[1] = "0";
            result[2] = buttonText;
        } else {
            result[0] = button + "_" + turnsHistory + "_" + status;
            result[1] = turnsHistory;
            result[2] = buttonText;
        }

        System.out.println("STARTING: " + result[0]);
        return result;
    }

    public List<List<InlineKeyboardButton>> makeInlineButtonsForGame(String call, String move, boolean isOver){

        System.out.println(call);

        if (isOver){
            System.out.println(
                    "OVER: move=" + move + " call=" + call
            );

            List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
            keyboardButtonsRow1.add(new InlineKeyboardButton().setText(makeCallBackData(call, "0", "0")[2]).setCallbackData(makeCallBackData(call, "0", "0")[0]));
            keyboardButtonsRow1.add(new InlineKeyboardButton().setText(makeCallBackData(call, "0", "1")[2]).setCallbackData(makeCallBackData(call, "0", "1")[0]));
            keyboardButtonsRow1.add(new InlineKeyboardButton().setText(makeCallBackData(call, "0", "2")[2]).setCallbackData(makeCallBackData(call, "0", "2")[0]));

            List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
            keyboardButtonsRow2.add(new InlineKeyboardButton().setText(makeCallBackData(call, "0", "3")[2]).setCallbackData(makeCallBackData(call, "0", "3")[0]));
            keyboardButtonsRow2.add(new InlineKeyboardButton().setText(makeCallBackData(call, "0", "4")[2]).setCallbackData(makeCallBackData(call, "0", "4")[0]));
            keyboardButtonsRow2.add(new InlineKeyboardButton().setText(makeCallBackData(call, "0", "5")[2]).setCallbackData(makeCallBackData(call, "0", "5")[0]));

            List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
            keyboardButtonsRow3.add(new InlineKeyboardButton().setText(makeCallBackData(call, "0", "6")[2]).setCallbackData(makeCallBackData(call, "0", "6")[0]));
            keyboardButtonsRow3.add(new InlineKeyboardButton().setText(makeCallBackData(call, "0", "7")[2]).setCallbackData(makeCallBackData(call, "0", "7")[0]));
            keyboardButtonsRow3.add(new InlineKeyboardButton().setText(makeCallBackData(call, "0", "8")[2]).setCallbackData(makeCallBackData(call, "0", "8")[0]));

            List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
            rowList.add(keyboardButtonsRow1);
            rowList.add(keyboardButtonsRow2);
            rowList.add(keyboardButtonsRow3);
            return rowList;

        } else {
            System.out.println("GOING: move=" + move + " call=" + call);
            List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
            keyboardButtonsRow1.add(new InlineKeyboardButton().setText(makeCallBackData(call, move, "0")[2]).setCallbackData(makeCallBackData(call, move, "0")[0]));
            keyboardButtonsRow1.add(new InlineKeyboardButton().setText(makeCallBackData(call, move, "1")[2]).setCallbackData(makeCallBackData(call, move, "1")[0]));
            keyboardButtonsRow1.add(new InlineKeyboardButton().setText(makeCallBackData(call, move, "2")[2]).setCallbackData(makeCallBackData(call, move, "2")[0]));

            List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
            keyboardButtonsRow2.add(new InlineKeyboardButton().setText(makeCallBackData(call, move, "3")[2]).setCallbackData(makeCallBackData(call, move, "3")[0]));
            keyboardButtonsRow2.add(new InlineKeyboardButton().setText(makeCallBackData(call, move, "4")[2]).setCallbackData(makeCallBackData(call, move, "4")[0]));
            keyboardButtonsRow2.add(new InlineKeyboardButton().setText(makeCallBackData(call, move, "5")[2]).setCallbackData(makeCallBackData(call, move, "5")[0]));

            List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
            keyboardButtonsRow3.add(new InlineKeyboardButton().setText(makeCallBackData(call, move, "6")[2]).setCallbackData(makeCallBackData(call, move, "6")[0]));
            keyboardButtonsRow3.add(new InlineKeyboardButton().setText(makeCallBackData(call, move, "7")[2]).setCallbackData(makeCallBackData(call, move, "7")[0]));
            keyboardButtonsRow3.add(new InlineKeyboardButton().setText(makeCallBackData(call, move, "8")[2]).setCallbackData(makeCallBackData(call, move, "8")[0]));

            List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
            rowList.add(keyboardButtonsRow1);
            rowList.add(keyboardButtonsRow2);
            rowList.add(keyboardButtonsRow3);
            return rowList;
        }

    }

}
