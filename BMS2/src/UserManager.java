import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;

class UserManager {

	private static UserManager instance = new UserManager();
	public static UserManager getInstance() {
	return instance;
	}

	private File file;
	
	private LinkedHashMap<String, User> users = new LinkedHashMap<String, User>();
	//LinkedHashMap �ڷᱸ���� HashMap �ڷᱸ������ �ڵ����� ���̴°Ÿ� ������ �������� �ش�.
	//Ű, ���� ���� ����Ǿ� �ִ� �������� �ش��ϴ� ���� ã�Ƴ���.
	
	//UserManager���� �Է¹��� ������ data.csv�� �̾Ƴ���
	public UserManager() {
		file = new File("./data.csv");
		if(file.exists()) {
			load();
		}
	}
	//data.csv ������ �о�ͼ� , ","�� split �Լ��� ���ڿ��� �����ϰ�, ��ū�� �����Ѵ�. 
	//�� ���پ� ��°�� �о����
	public void load() {
		try {
			var fr = new FileReader(file);
			var br = new BufferedReader(fr);
			String line = br.readLine();
			while((line = br.readLine()) != null) {
				String[] tokens = line.split(",");
				
				User user = new User();
				user.studentName = tokens[0];
				user.studentNumber = tokens[1];
				user.grade = tokens[2];
				user.studentID = tokens[3];
				user.studentPW = tokens[4];
				user.phoneNumber = tokens[5];
				user.email = tokens[6];
				user.address = tokens[7];
				
				System.out.println(user.studentName);
				
				users.put(user.studentID, user);
			}

			br.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//�����Ѵ�.
	public void save() {
		// StudentName,StudentNumber,Grade,StudentID,StudentPW,PhoneNumber,Email,Address
		try {
			FileWriter fw = new FileWriter(file);
			fw.append("StudentName,StudentNumber,Grade,StudentID,StudentPW,PhoneNumber,Email,Address\n");
			
			for(var entry : users.entrySet()) {
				User user = entry.getValue();
				
				String[] tokens = new String[]{
					user.studentName,
					user.studentNumber,
					user.grade,
					user.studentID,
					user.studentPW,
					user.phoneNumber,
					user.email,
					user.address
				};

				fw.append(String.join(",", tokens) + "\n"); //���پ� �����Ͽ� ������ ����
			}
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//ID���� ã��
	public User getUser(String studentID) {
		return users.get(studentID);
	}
//	public User loggedUser = null;
//	public bool login(String studentID, String password)
	//Ű, ���� ���� �� ��°�� �����Ѵ�.
	public void createUser(User user) {
		users.put(user.studentID, user);
		save();
	}
	

}