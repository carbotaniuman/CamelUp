package immutable;

import java.awt.Color;
import java.util.Objects;

public class Camel {
	private Color color;

	// Constructor
	public Camel(Color c) {
		color = c;
	}

	// getColor
	public Color getColor() {
		return color;
	}

	// equals
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Camel)) {
			return false;
		}
		Camel c = (Camel) obj;
		return Objects.equals(color, c.getColor());
	}

	// hashcode
	@Override
	public int hashCode() {
		return color.hashCode() * 31 - 1;
	}

	// toString
	@Override
	public String toString() {
		return "Camel: " + color;
	}
}
