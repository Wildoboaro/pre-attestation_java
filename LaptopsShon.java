import java.util.*;
import java.util.stream.Collectors;

class Laptop {
    int size;
    String vendor;
    int ramOnBoard;
    String driveType;
    int driveSize;
    String installedOS;

    public Laptop(int size, String vendor, int ramOnBoard, String driveType, int driveSize, String installedOS) {
        this.size = size;
        this.vendor = vendor;
        this.ramOnBoard = ramOnBoard;
        this.driveType = driveType;
        this.driveSize = driveSize;
        this.installedOS = installedOS;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "Диагональ ='" + size + '\'' +
                ", производитель ='" + vendor + '\'' +
                ", объем оперативной памяти =" + ramOnBoard +
                ", тип накопителя =" + driveType +
                ", объем накопителя =" + driveSize +
                ", os='" + installedOS + '\'' +

                '}';
    }
}

public class LaptopsShon {
    public static void main(String[] args) {
        Set<Laptop> laptops = new HashSet<>();
        laptops.add(new Laptop(15, "Dell", 16, "SSD", 512, "Windows 11"));
        laptops.add(new Laptop(12, "Apple", 8, "SSD", 256, "MacOS 14 Sonoma"));
        laptops.add(new Laptop(17, "Lenovo", 32, "HDD", 512, "Не установлена"));
        laptops.add(new Laptop(15, "Asus", 16, "HDD", 512, "CentOs 7"));

        Map<String, Object> filters = new HashMap<>();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Правметры для выборки(фильтр):");
        System.out.println("1 - диагональ");
        System.out.println("2 - производитель");
        System.out.println("3 - объем установленной оперативной памяти");
        System.out.println("4 - тип установленного накопителя");
        System.out.println("5 - объем установленного накопителя");
        System.out.println("4 - тип установленного накопителя");        
        System.out.println("0 - Завершить выбор");

        int choice;
        while (true) {
            choice = scanner.nextInt();
            if (choice == 0) {
                break;
            }
            switch (choice) {
                
                case 1:
                    System.out.println("Введиет желаемую диагональ:");
                    filters.put("size", scanner.nextInt());
                    break;
                case 2:
                    System.out.println("Ввидите наименование желаемого производителя:");
                    filters.put("vendor", scanner.next());
                    break;
                case 3:
                    System.out.println("Ввидите минимальный достаточный объем оперативной памяти:");
                    filters.put("ramOnBoard", scanner.nextInt());
                    break;
                case 4:
                    System.out.println("Введите желаемый тип накопителя:");
                    filters.put("driveType", scanner.next());
                    break;
                case 5:
                    System.out.println("Введите минимальный объем накопителя:");
                    filters.put("driveSize", scanner.nextInt());
                    break;
                case 6:
                    System.out.println("Введите назваение желаемоы операционной системы:");
                    filters.put("installedOS", scanner.next());
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }

        Set<Laptop> filteredLaptops = laptops.stream()
                .filter(laptop -> filters.getOrDefault("size", 0) instanceof Integer && laptop.size >= (int) filters.getOrDefault("size", 0))
                .filter(laptop -> filters.getOrDefault("vendor", "").equals("") || laptop.vendor.equalsIgnoreCase((String) filters.getOrDefault("vendor", "")))
                .filter(laptop -> filters.getOrDefault("ramOnBoard", 0) instanceof Integer && laptop.ramOnBoard >= (int) filters.getOrDefault("ramOnBoard", 0))
                .filter(laptop -> filters.getOrDefault("driveType", "").equals("") || laptop.driveType.equalsIgnoreCase((String) filters.getOrDefault("driveType", "")))
                .filter(laptop -> filters.getOrDefault("driveSize", 0) instanceof Integer && laptop.driveSize >= (int) filters.getOrDefault("driveSize", 0))
                .filter(laptop -> filters.getOrDefault("installedOS", "").equals("") || laptop.installedOS.equalsIgnoreCase((String) filters.getOrDefault("installedOS", "")))
                .collect(Collectors.toSet());

        System.out.println("Отфильтрованные ноутбуки:");
        for (Laptop laptop : filteredLaptops) {
            System.out.println(laptop);
        }
    }
}