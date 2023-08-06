package com.gildedtros;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

class GildedTrosTest {

	@Test
	void testNormalItemDegradation() {
		Item[] items = new Item[] { new Item("Elixir of the SOLID", 5, 7) };
		GildedTros app = new GildedTros(items);
		app.updateQuality();
		assertAll("Verifiy Item Properties", () -> assertEquals("Elixir of the SOLID", app.items[0].name),
				() -> assertEquals(4, app.items[0].sellIn), () -> assertEquals(6, app.items[0].quality));
	}

	@Test
	void testEnsureNoNegativeQualityExists() {
		Item[] items = new Item[] { new Item("Elixir of the SOLID", 5, 0) };
		GildedTros app = new GildedTros(items);
		app.updateQuality();
		assertFalse(app.items[0].quality == -1);
	}

	@Test
	void testMultipleItemsDegradation() {
		Item[] items = new Item[] { new Item("Ring of Cleansening Code", 20, 20), new Item("Elixir of the SOLID", 5, 7),
				new Item("Backstage passes for HAXX", 10, 10) };
		GildedTros app = new GildedTros(items);

		app.updateQuality();

		assertAll("Verifiy Ring Properties", () -> assertEquals("Ring of Cleansening Code", app.items[0].name),
				() -> assertEquals(19, app.items[0].sellIn), () -> assertEquals(19, app.items[0].quality));

		assertAll("Verifiy Elixir Properties", () -> assertEquals("Elixir of the SOLID", app.items[1].name),
				() -> assertEquals(4, app.items[1].sellIn), () -> assertEquals(6, app.items[1].quality));

		assertAll("Verifiy Backstage Pass Properties",
				() -> assertEquals("Backstage passes for HAXX", app.items[2].name),
				() -> assertEquals(9, app.items[2].sellIn), () -> assertEquals(12, app.items[2].quality));
	}

	@Test
	void testLegendaryItemDegradation() {
		Item[] items = new Item[] { new Item("B-DAWG Keychain", 0, 80) };
		GildedTros app = new GildedTros(items);
		app.updateQuality();
		assertAll("Verifiy Item Properties", () -> assertEquals("B-DAWG Keychain", app.items[0].name),
				() -> assertEquals(0, app.items[0].sellIn), () -> assertEquals(80, app.items[0].quality));
	}

	@Test
	void testGoodWineDegradation() {
		Item[] items = new Item[] { new Item("Good Wine", 2, 0) };
		GildedTros app = new GildedTros(items);
		app.updateQuality();
		assertAll("Verifiy Item Properties", () -> assertEquals("Good Wine", app.items[0].name),
				() -> assertEquals(1, app.items[0].sellIn), () -> assertEquals(1, app.items[0].quality));
	}

	@Test
	void testBackstagePassDegradation() {
		Item[] items = new Item[] { new Item("Backstage passes for Re:Factor", 10, 40),
				new Item("Backstage passes for Re:Factor", 15, 20), new Item("Backstage passes for HAXX", 5, 40) };

		GildedTros app = new GildedTros(items);
		app.updateQuality();

		assertAll("Verifiy First Backstage Pass Properties",
				() -> assertEquals("Backstage passes for Re:Factor", app.items[0].name),
				() -> assertEquals(9, app.items[0].sellIn), () -> assertEquals(42, app.items[0].quality));

		assertAll("Verifiy Second Backstage Pass Properties",
				() -> assertEquals("Backstage passes for Re:Factor", app.items[1].name),
				() -> assertEquals(14, app.items[1].sellIn), () -> assertEquals(21, app.items[1].quality));

		assertAll("Verifiy Third Backstage Pass Properties",
				() -> assertEquals("Backstage passes for HAXX", app.items[2].name),
				() -> assertEquals(4, app.items[2].sellIn), () -> assertEquals(43, app.items[2].quality));
	}

	@Test
	void testMaximumQuality() {
		Item[] items = new Item[] { new Item("Backstage passes for Re:Factor", 10, 49) };
		GildedTros app = new GildedTros(items);
		app.updateQuality();
		assertAll("Verifiy First Backstage Pass Properties",
				() -> assertEquals("Backstage passes for Re:Factor", app.items[0].name),
				() -> assertEquals(9, app.items[0].sellIn), () -> assertEquals(50, app.items[0].quality));
	}

	@Test
	void testMinimumQuality() {
		Item[] items = new Item[] { new Item("Elixir of the SOLID", 6, 0) };
		GildedTros app = new GildedTros(items);
		app.updateQuality();
		assertAll("Verifiy Item Properties", () -> assertEquals("Elixir of the SOLID", app.items[0].name),
				() -> assertEquals(5, app.items[0].sellIn), () -> assertEquals(0, app.items[0].quality));
	}

	@Test
	void testExpiredItemDegradation() {
		Item[] items = new Item[] { new Item("Elixir of the SOLID", 0, 10) };
		GildedTros app = new GildedTros(items);
		app.updateQuality();
		assertAll("Verifiy Item Properties", () -> assertEquals("Elixir of the SOLID", app.items[0].name),
				() -> assertEquals(-1, app.items[0].sellIn), () -> assertEquals(8, app.items[0].quality));
	}

	@Test
	void testFullWeekItemDegradation() {
		Item[] items = new Item[] { new Item("Ring of Cleansening Code", 4, 20) };
		GildedTros app = new GildedTros(items);
		for (int day = 0; day < 7; day++) {
			app.updateQuality();
		}
		assertAll("Verifiy Item Properties", () -> assertEquals("Ring of Cleansening Code", app.items[0].name),
				() -> assertEquals(-3, app.items[0].sellIn), () -> assertEquals(10, app.items[0].quality));
	}

	@Test
	void testFullWeekMultipleItemsDegradation() {
		Item[] items = new Item[] { new Item("Ring of Cleansening Code", 20, 20), new Item("Elixir of the SOLID", 5, 7),
				new Item("Backstage passes for HAXX", 10, 10) };
		GildedTros app = new GildedTros(items);
		for (int day = 0; day < 7; day++) {
			app.updateQuality();
		}
		assertAll("Verifiy Ring Properties", () -> assertEquals("Ring of Cleansening Code", app.items[0].name),
				() -> assertEquals(13, app.items[0].sellIn), () -> assertEquals(13, app.items[0].quality));

		assertAll("Verifiy Elixir Properties", () -> assertEquals("Elixir of the SOLID", app.items[1].name),
				() -> assertEquals(-2, app.items[1].sellIn), () -> assertEquals(0, app.items[1].quality));

		assertAll("Verifiy Backstage Pass Properties",
				() -> assertEquals("Backstage passes for HAXX", app.items[2].name),
				() -> assertEquals(3, app.items[2].sellIn), () -> assertEquals(27, app.items[2].quality));
	}

}
