package enemy;

import java.util.Comparator;

public class EnemySorter implements Comparator<Enemy> {
	@Override
	public int compare(Enemy e1, Enemy e2) {
		return e1.compareTo(e2);
	}
}