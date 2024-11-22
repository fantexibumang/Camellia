import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PersonalBillingManagementSystem {
    private static List<Transaction> incomeList = new ArrayList<>();
    private static List<Transaction> expenseList = new ArrayList<>();
    private static double monthlyBudget = 0;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            switch (choice) {
                case 1:
                    recordIncome();
                    break;
                case 2:
                    recordExpense();
                    break;
                case 3:
                    viewAllBills();
                    break;
                case 4:
                    queryBill();
                    break;
                case 5:
                    setMonthlyBudget();
                    break;
                case 6:
                    viewMonthlyStatistics();
                    break;
                case 7:
                    running = false;
                    System.out.println("系统已退出。");
                    break;
                default:
                    System.out.println("无效选项，请重新输入。");
            }
        }
    }//好麻烦的作业

    private static void showMenu() {
        System.out.println("=================================");
        System.out.println("欢迎使用个人账单管理系统");
        System.out.println("=================================");
        System.out.println("请选择操作：");
        System.out.println("1. 记录收入");
        System.out.println("2. 记录支出");
        System.out.println("3. 查看所有账单");
        System.out.println("4. 查询账单");
        System.out.println("5. 设置月度预算");
        System.out.println("6. 查看月度统计报告");
        System.out.println("7. 退出系统");
        System.out.print("请输入选项序号：");
    }

    private static void recordIncome() {
        System.out.println("=================================");
        System.out.println("请输入收入信息：");
        System.out.print("日期（YYYY-MM-DD）：");
        String date = scanner.nextLine();
        System.out.print("金额：");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("类别（如工资、奖金等）：");
        String category = scanner.nextLine();
        System.out.print("备注：");
        String note = scanner.nextLine();

        if (amount <= 0) {
            System.out.println("收入金额必须为正数。");
            return;
        }

        incomeList.add(new Transaction(date, amount, category, note));
        System.out.println("收入已成功记录！");
        promptReturnToMenu();
    }

    private static void recordExpense() {
        System.out.println("================================");
        System.out.println("请输入支出信息：");
        System.out.print("日期（YYYY-MM-DD）：");
        String date = scanner.nextLine();
        System.out.print("金额：");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("类别（如餐饮、交通、购物等）：");
        String category = scanner.nextLine();
        System.out.print("备注：");
        String note = scanner.nextLine();

        if (amount <= 0) {
            System.out.println("支出金额必须为正数。");
            return;
        }

        expenseList.add(new Transaction(date, amount, category, note));
        System.out.println("支出已成功记录！");
        promptReturnToMenu();
    }
//emmmmmm
    private static void viewAllBills() {
        System.out.println("=================================");
        System.out.println("所有账单：");
        System.out.println("收入：");
        for (Transaction income : incomeList) {
            System.out.println(income);
        }
        System.out.println("支出：");
        for (Transaction expense : expenseList) {
            System.out.println(expense);
        }
        promptReturnToMenu();
    }

    private static void queryBill() {
        System.out.print("请输入要查询的日期（YYYY-MM-DD）：");
        String date = scanner.nextLine();
        System.out.println("查询结果：");
        boolean found = false;

        for (Transaction income : incomeList) {
            if (income.date.equals(date)) {
                System.out.println("收入: " + income);
                found = true;
            }
        }

        for (Transaction expense : expenseList) {
            if (expense.date.equals(date)) {
                System.out.println("支出: " + expense);
                found = true;
            }
        }

        if (!found) {
            System.out.println("没有找到该日期的账单。");
        }
        promptReturnToMenu();
    }

    private static void setMonthlyBudget() {
        System.out.print("请输入每月预算限额：");
        monthlyBudget = scanner.nextDouble();
        System.out.println("月度预算已设置为：" + monthlyBudget);
        promptReturnToMenu();
    }

    private static void viewMonthlyStatistics() {
        double totalIncome = 0;
        double totalExpense = 0;

        for (Transaction income : incomeList) {
            totalIncome += income.amount;
        }

        for (Transaction expense : expenseList) {
            totalExpense += expense.amount;
        }

        double remainingBudget = monthlyBudget - totalExpense;

        System.out.println("=================================");
        System.out.println("月度统计报告：");
        System.out.println("总收入：" + totalIncome);
        System.out.println("总支出：" + totalExpense);
        System.out.println("剩余可用预算：" + remainingBudget);
        promptReturnToMenu();
    }

    private static void promptReturnToMenu() {
        System.out.println("按任意键返回主菜单...");
        scanner.nextLine(); // Wait for user input
    }
}

class Transaction {
    String date;
    double amount;
    String category;
    String note;

    public Transaction(String date, double amount, String category, String note) {
        this.date = date;
        this.amount = amount;
        this.category = category;
        this.note = note;
    }

    @Override
    public String toString() {
        return "日期: " + date + ", 金额: " + amount + ", 类别: " + category + ", 备注: " + note;
    }
}

