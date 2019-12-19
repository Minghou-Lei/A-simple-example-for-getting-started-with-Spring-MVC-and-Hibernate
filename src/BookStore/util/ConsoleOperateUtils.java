package BookStore.util;

import BookStore.service.impl.BookStoreServiceImpl;
import BookStore.service.impl.UserServiceImpl;

import java.util.Date;
import java.util.Scanner;

import static java.lang.System.exit;

public class ConsoleOperateUtils {

	private static void inBookStore(BookStoreServiceImpl service) throws InterruptedException {
		for (; ; ) {
			Scanner scanner = new Scanner(System.in);
			System.out.println(" ______     __  __     ______     ______   ______   __     __   __     ______        ______   ______     ______     ______    \n" +
					"/\\  ___\\   /\\ \\_\\ \\   /\\  __ \\   /\\  == \\ /\\  == \\ /\\ \\   /\\ \"-.\\ \\   /\\  ___\\      /\\  == \\ /\\  __ \\   /\\  ___\\   /\\  ___\\   \n" +
					"\\ \\___  \\  \\ \\  __ \\  \\ \\ \\/\\ \\  \\ \\  _-/ \\ \\  _-/ \\ \\ \\  \\ \\ \\-.  \\  \\ \\ \\__ \\     \\ \\  _-/ \\ \\  __ \\  \\ \\ \\__ \\  \\ \\  __\\   \n" +
					" \\/\\_____\\  \\ \\_\\ \\_\\  \\ \\_____\\  \\ \\_\\    \\ \\_\\    \\ \\_\\  \\ \\_\\\\\"\\_\\  \\ \\_____\\     \\ \\_\\    \\ \\_\\ \\_\\  \\ \\_____\\  \\ \\_____\\ \n" +
					"  \\/_____/   \\/_/\\/_/   \\/_____/   \\/_/     \\/_/     \\/_/   \\/_/ \\/_/   \\/_____/      \\/_/     \\/_/\\/_/   \\/_____/   \\/_____/ \n" +
					"                                                                                                                              ");
			System.out.println("欢迎您进入用户页面！");
			Thread.sleep(200);
			System.out.println("打印书单请按 1 ，购书请按 2 ，打印订单请按 3 ，管理账户请按 4 ，删除订单请按 5 ， 退出请按 Q ：");
			switch (scanner.nextLine()) {
				case "1":
					service.printBooks();
					break;
				case "2":
					System.out.println("请输入书本ID：");
					String bookid = scanner.nextLine();
					System.out.println("请输入购买本数：");
					String qty = scanner.nextLine();
					try {
						service.buyBook(Integer.parseInt(bookid), Integer.parseInt(qty));
						System.out.println("购买成功！");
					} catch (Exception e) {
						System.out.println("购买失败！");
						break;
					}
					break;
				case "3":
					service.printOrderInfo();
					break;
				case "4":
					UserServiceImpl userService = new UserServiceImpl(service);
					inUserManage(userService);
					break;
				case "5":
					System.out.println("请输入要删除的OrderID：");
					String orderID = scanner.nextLine();
					try {
						service.cancelOrder(Integer.parseInt(orderID));
						System.out.println("成功删除！");
					} catch (Exception e) {
						System.out.println("没有这样的订单或订单已经结束！");
					}
					break;
				case "Q":
					return;
			}
		}
	}

	private static void inUserManage(UserServiceImpl userService) throws InterruptedException {
		Scanner scanner = new Scanner(System.in);
		for (; ; ) {
			System.out.println(" __  __     ______     ______     ______        __    __     ______     __   __     ______     ______     ______     __    __     ______     __   __     ______  \n" +
					"/\\ \\/\\ \\   /\\  ___\\   /\\  ___\\   /\\  == \\      /\\ \"-./  \\   /\\  __ \\   /\\ \"-.\\ \\   /\\  __ \\   /\\  ___\\   /\\  ___\\   /\\ \"-./  \\   /\\  ___\\   /\\ \"-.\\ \\   /\\__  _\\ \n" +
					"\\ \\ \\_\\ \\  \\ \\___  \\  \\ \\  __\\   \\ \\  __<      \\ \\ \\-./\\ \\  \\ \\  __ \\  \\ \\ \\-.  \\  \\ \\  __ \\  \\ \\ \\__ \\  \\ \\  __\\   \\ \\ \\-./\\ \\  \\ \\  __\\   \\ \\ \\-.  \\  \\/_/\\ \\/ \n" +
					" \\ \\_____\\  \\/\\_____\\  \\ \\_____\\  \\ \\_\\ \\_\\     \\ \\_\\ \\ \\_\\  \\ \\_\\ \\_\\  \\ \\_\\\\\"\\_\\  \\ \\_\\ \\_\\  \\ \\_____\\  \\ \\_____\\  \\ \\_\\ \\ \\_\\  \\ \\_____\\  \\ \\_\\\\\"\\_\\    \\ \\_\\ \n" +
					"  \\/_____/   \\/_____/   \\/_____/   \\/_/ /_/      \\/_/  \\/_/   \\/_/\\/_/   \\/_/ \\/_/   \\/_/\\/_/   \\/_____/   \\/_____/   \\/_/  \\/_/   \\/_____/   \\/_/ \\/_/     \\/_/ \n" +
					"                                                                                                                                                                 ");
			System.out.println(userService.getUser().getUserName() + " , 您已进入账户管理页面！");
			Thread.sleep(200);
			System.out.println("更改密码请按 1 ，注销账户请按 2 ，退出请按 Q ：");
			switch (scanner.nextLine()) {
				case "1":
					System.out.println("请输入旧密码:");
					String oldpassword = scanner.nextLine();
					if (!oldpassword.equals(userService.getUser().getPassword())) {
						System.out.println("旧密码不正确！");
						break;
					} else {
						System.out.println("请输入新密码：");
						String newPassword = scanner.nextLine();
						System.out.println("请再次输入新密码：");
						if (!scanner.nextLine().equals(newPassword)) {
							System.out.println("两次输入不一致！");
							break;
						} else {
							userService.changePassword(newPassword);
							System.out.println("修改成功！");
							break;
						}
					}
				case "2": {
					System.out.println("是否真的要注销账户？ ( Y/N )  :");
					switch (scanner.nextLine()) {
						case "Y":
							try {
								userService.deleteUser();
								System.out.println("注销成功！");
								exit(0);
								return;
							} catch (Exception e) {
								System.out.println("你的账户内还有未处理的订单，禁止注销！");
							}
							break;
						case "N":
							break;
					}
					break;
				}
				case "Q":
					return;
			}

		}
	}


	public static void main(String[] args) throws InterruptedException {
		mainLoop();
	}

	public static void mainLoop() throws InterruptedException {
		Scanner scanner = new Scanner(System.in);
		for (; ; ) {
			System.out.println("▄▄▄▄·             ▄ •▄ .▄▄ · ▄▄▄▄▄      ▄▄▄  ▄▄▄ .\n" +
					"▐█ ▀█▪▪     ▪     █▌▄▌▪▐█ ▀. •██  ▪     ▀▄ █·▀▄.▀·\n" +
					"▐█▀▀█▄ ▄█▀▄  ▄█▀▄ ▐▀▀▄·▄▀▀▀█▄ ▐█.▪ ▄█▀▄ ▐▀▀▄ ▐▀▀▪▄\n" +
					"██▄▪▐█▐█▌.▐▌▐█▌.▐▌▐█.█▌▐█▄▪▐█ ▐█▌·▐█▌.▐▌▐█•█▌▐█▄▄▌\n" +
					"·▀▀▀▀  ▀█▄▀▪ ▀█▄▀▪·▀  ▀ ▀▀▀▀  ▀▀▀  ▀█▄▀▪.▀  ▀ ▀▀▀ ");
			System.out.println("--------  欢迎使用BookStore命令行管理系统！ ----------");
			Thread.sleep(200);
			System.out.println("登录请按 1 ，注册请按 2 ,退出请按 Q ：");
			switch (scanner.nextLine()) {
				case "1":
					System.out.println("请输入账户：");
					String username = scanner.nextLine();
					System.out.println("请输入密码：");
					String password = scanner.nextLine();
					try {
						BookStoreServiceImpl service = new BookStoreServiceImpl(username, password);
						inBookStore(service);
					} catch (Exception e) {
						System.out.println("用户名或密码错误！");
						break;
					}
					break;
				case "2":
					System.out.println("请输入账户：");
					String newUsername = scanner.nextLine();
					System.out.println("请输入密码：");
					String newPassword = scanner.nextLine();
					System.out.println("请输入性别  ( Female/Male )  ：");
					String sex = scanner.nextLine();
					System.out.println("请输入生日(yyyy-MM-dd)：");
					Date birthday = java.sql.Date.valueOf(scanner.nextLine());
					UserServiceImpl userService = new UserServiceImpl();
					try {
						userService.register(newUsername, newPassword, sex, birthday, null);
						System.out.println("注册成功！ 现在开始登陆......");
						Thread.sleep(200);
					} catch (Exception e) {
						System.out.println("注册失败！");
						break;
					}
					break;
				case "Q":
					return;
			}

		}
	}
}
