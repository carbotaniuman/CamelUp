package immutable;

import java.awt.Color;
import java.util.Objects;

import gamestate.GameState;

public class Camel {
	private final Color color;

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
		return "Camel: " + GameState.COLORBIMAP.inverse().get(color);
	}
}
