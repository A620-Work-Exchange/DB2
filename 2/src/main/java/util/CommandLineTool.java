package util;

import dao.BillDAO;
import dao.BundleDAO;
import dao.ConsumptionDAO;
import dao.UserDAO;
import domain.Bundle;
import domain.User;
import domain.enumeration.BundleType;

import java.util.Scanner;

/**
 * 封装的命令行小玩具
 */
public class CommandLineTool {
    private String cmd;

    private UserDAO userDAO = new UserDAO();
    private ConsumptionDAO consumptionDAO = new ConsumptionDAO();
    private BundleDAO bundleDAO = new BundleDAO();
    private BillDAO billDAO = new BillDAO();


    public void init() {
        while (true) {
            try {
                System.out.println("请输入命令...");
                Scanner input = new Scanner(System.in);
                cmd = input.nextLine().trim();
                if(cmd.equals("quit") || cmd.equals("exit")){
                    break;
                }
                analyzeCMD(cmd);
                System.out.println("...");
            }catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void analyzeCMD(String cmd) {

        String[] cmdArr = cmd.split(" ");
        if( cmdArr.length < 0 ){
            System.out.println("请输入命令...");

        }
        String cmdBegin = cmdArr[0];
        String username;
        switch (cmdBegin) {
            //add user [username] [password]
            case "add":
                username = cmdArr[2];
                String password = cmdArr[3];
                userDAO.addUser(username, password);
                break;

                //search buddle [username]
            case "search":
                username = cmdArr[2];
                bundleDAO.listBundleByUsername(username);
                break;

                //order next [buddleType] [username] [period]
            case "order":
                String efficientDay = cmdArr[1];
                username = cmdArr[3];
                int period = Integer.parseInt(cmdArr[4]);
                BundleType bundleType = transStrToBunndleType(cmdArr[2]);

                if (efficientDay.equals("imm") ) {
                    bundleDAO.addBundleImmediately(bundleType, username, period);
                    break;
                } else if ( efficientDay.equals("next") ) {
                    bundleDAO.addBundleNextMonth(bundleType, username, period);
                }
                break;

                //deorder next [username] [buddleId]
            case "deorder":
                String ddl = cmdArr[1];
                username = cmdArr[2];
                int buddleId = Integer.parseInt(cmdArr[3]);
                if(ddl.equals("imm")) {
                    bundleDAO.removeBundleImmediately(username, buddleId);
                    break;
                }else if(ddl.equals("next")) {
                    bundleDAO.removeBundleUntilNextMonth(username, buddleId);
                    break;
                }
                break;

                //fee call [username] [callUsage]
            case "fee":
                username = cmdArr[2];
                double usage = Double.parseDouble(cmdArr[3]);
                switch (cmdArr[1]) {
                    case "call":
                        consumptionDAO.addCallUsage(username, usage);
                        break;
                    case "sms":
                        consumptionDAO.addSMSUsage(username, (int) (usage));
                        break;
                    case "local":
                        consumptionDAO.addLocalDataUsage(username, usage);
                        break;
                    case "dome":
                        consumptionDAO.addDomesticDataUsage(username, usage);
                        break;
                }

                //bill [dateToMonth] [username]
            case "bill":
                String dateToMonth = cmdArr[1];
                username = cmdArr[2];
                billDAO.addBill(dateToMonth, username);
                break;

            case "topup":
                username = cmdArr[1];
                double amount = Double.parseDouble(cmdArr[2]);
                userDAO.topUp(username, amount);
                break;

            case "check":
                username = cmdArr[1];
                System.out.println("余额为" + userDAO.checkBalance(username) + "元");
                break;

        }
    }

    private BundleType transStrToBunndleType(String str) {

        BundleType bundleType = BundleType.Base;
        switch (str) {
            case "call":
                bundleType = BundleType.Call;
                break;
            case "sms":
                bundleType = BundleType.SMS;
                break;
            case "local":
                bundleType = BundleType.Local;
                break;
            case "dome":
                bundleType = BundleType.Domestic;
                break;
        }
        return bundleType;
    }

}
