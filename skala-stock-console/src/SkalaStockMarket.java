import java.util.Random;
import java.util.Scanner;

public class SkalaStockMarket {
    private PlayerRepository playerRepository = new PlayerRepository();
    private StockRepository stockRepository = new StockRepository();
    private Player player = null;

    public void start() {

        // 주식 레파지토리에서 주식 정보를 불러옴
        stockRepository.loadStockList();

        // 플레이어 레파지토리에서 플레이어 정보를 불러옴
        playerRepository.loadPlayerList();

        // 콘솔로 입력을 받을 수 있도록 스캐너 설정
        Scanner scanner = new Scanner(System.in);

        System.out.print("플레이어 ID를 입력하세요: ");
        String playerId = scanner.nextLine();
        player = playerRepository.findPlayer(playerId);
        if (player == null) { // 새로운 플레이어
            player = new Player(playerId);

            System.out.print("초기 투자금을 입력하세요: ");
            int money = scanner.nextInt();
            player.setPlayerMoney(money);
            playerRepository.addPlayer(player);
        }
        displayPlayerStocks();

        // 프로그램 루프
        boolean running = true;
        while (running) {
            System.out.println("\n=== 스칼라 주식 프로그램 메뉴 ===");
            System.out.println("1. 보유 주식 목록");
            System.out.println("2. 주식 구매");
            System.out.println("3. 주식 판매");
            System.out.println("4. 주식 정보 업데이트");
            System.out.println("5. 전체 주식 판매");
            System.out.println("0. 프로그램 종료");

            System.out.print("선택: ");
            int code = scanner.nextInt();

            switch (code) {
                case 1:
                    displayPlayerStocks();
                    break;
                case 2:
                    buyStock(scanner);
                    break;
                case 3:
                    sellStock(scanner);
                    break;
                case 4:
                    changeValue();
                    break;
                case 5:
                    allSell();
                    break;
                case 0:
                    System.out.println("프로그램을 종료합니다...Bye");
                    running = false;
                    break;
                default:
                    System.out.println("올바른 번호를 선택하세요.");
            }
        }

        scanner.close();
    }

    // 플레이어의 보유 주식 목록 표시
    private void displayPlayerStocks() {
        System.out.println("\n######### 플레이어 정보 #########");
        System.out.println("- 플레이어ID : " + player.getplayerId());
        System.out.println("- 보유금액 : " + player.getPlayerMoney());
        System.out.println("- 보유 주식 목록");
        System.out.println(player.getPlayerStocksForMenu());
    }

    // 주식 목록 표시
    private void displayStockList() {
        System.out.println("\n=== 주식 목록 ===");
        System.out.println(stockRepository.getStockListForMenu());
    }

    // 주식 구매
    private void buyStock(Scanner scanner) {
        System.out.println("\n구매할 주식 번호를 선택하세요:");
        displayStockList();

        System.out.print("선택: ");
        int index = scanner.nextInt() - 1;

        Stock selectedStock = stockRepository.findStock(index);
        if (selectedStock != null) {
            System.out.print("구매할 수량을 입력하세요: ");
            int quantity = scanner.nextInt();

            int totalCost = selectedStock.getStockPrice() * quantity;
            int playerMoney = player.getPlayerMoney();
            if (totalCost <= playerMoney) {
                player.setPlayerMoney(playerMoney - totalCost);
                player.addStock(new PlayerStock(selectedStock, quantity));
                System.out.println(quantity + "주를 구매했습니다! 남은 금액: " + player.getPlayerMoney());

                // 변경된 내용을 파일로 저장
                playerRepository.savePlayerList();
            } else {
                System.out.println("ERROR: 금액이 부족합니다.");
            }
        } else {
            System.out.println("ERROR: 잘못된 선택입니다.");
        }
    }

    // 주식 판매
    private void sellStock(Scanner scanner) {
        System.out.println("\n판매할 주식 번호를 선택하세요:");
        displayPlayerStocks();
        int realizedprofit = 0;
    
        System.out.print("선택: ");
        int index = scanner.nextInt() - 1;

        PlayerStock playerStock = player.findStock(index);
        if (playerStock != null) {
            System.out.print("판매할 수량을 입력하세요: ");
            int quantity = scanner.nextInt();

            // 어얼리 리턴
            if (quantity > playerStock.getStockQuantity()) {
                System.out.println("ERROR: 수량이 부족합니다.");
                return;
            }

            Stock baseStock = stockRepository.findStock(playerStock.getStockName());
            int playerMoney = player.getPlayerMoney() + baseStock.getStockPrice() * quantity;
            player.setPlayerMoney(playerMoney);
            realizedprofit = baseStock.getStockPrice()*quantity-playerStock.stockPrice*quantity;
            playerStock.setStockQuantity(playerStock.getStockQuantity() - quantity);
            player.updatePlayerStock(playerStock);
            System.out.println("구입금 "+playerStock.stockPrice+"판매금"+baseStock.getStockPrice());
            System.out.println("판매 완료!: 실현 이익은"+realizedprofit+"원 입니다. 남은 금액: " + player.getPlayerMoney());
            // 변경된 내용을 파일로 저장
            playerRepository.savePlayerList();

        } else {
            System.out.println("ERROR: 잘못된 선택입니다.");
        }
    }
    private void allSell() {
        System.out.println("\n일괄 판매..");
        displayPlayerStocks();
        int realizedprofit = 0;
        int cnt = player.getPlayerStocks().size();
        for (int i =0 ; i < cnt;  i++) {
            PlayerStock playerStock = player.findStock(0);
             if(playerStock != null){
                Stock baseStock = stockRepository.findStock(playerStock.getStockName());
                realizedprofit = baseStock.getStockPrice()*playerStock.getStockQuantity()-playerStock.stockPrice*playerStock.getStockQuantity();
                player.setPlayerMoney(player.getPlayerMoney() + baseStock.getStockPrice() * playerStock.getStockQuantity());
                playerStock.setStockQuantity(0);
                player.updatePlayerStock(playerStock);
                System.out.println("구입금 "+playerStock.stockPrice+"판매금"+baseStock.getStockPrice());
                System.out.println("판매 완료!: 실현 이익은"+realizedprofit+"원 입니다. 남은 금액: " + player.getPlayerMoney());
                // 변경된 내용을 파일로 저장
                playerRepository.savePlayerList();
             }
        
        }
           
    }   
       
    private void changeValue() {
    System.out.println("\n----- 하루 진행 중: 시장 상황 업데이트 -----");
    Random random = new Random(); // Random 객체 생성

    String[] stocks = stockRepository.getStockListForMenu().split(System.lineSeparator());
    for (int i = 0; i < stocks.length; i++) {
        Stock stock = stockRepository.findStock(i);
        if (stock != null) {
            // 수정: -5% ~ +5% 사이의 변동률
            double changePercent = (random.nextDouble() * 10) - 5;
            int oldPrice = stock.getStockPrice();
            int newPrice = (int) Math.max(1, oldPrice + oldPrice * changePercent / 100);
            stock.setStockPrice(newPrice);
            System.out.println(stock.getStockName() + " 가격이 " + oldPrice + "에서 " + newPrice +
                " (변동률: " + String.format("%.2f", changePercent) + "%)로 변경되었습니다.");
        }
    }

    stockRepository.saveStockList(); // 수정된 가격 저장
}
}
