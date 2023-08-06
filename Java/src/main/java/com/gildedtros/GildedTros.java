package com.gildedtros;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class GildedTros {
	Item[] items;

	private final static String GOOD_WINE = "Good Wine";
	private final static String BACKSTAGE_PASSES = "Backstage passes";
	private final static Set<String> LEGENDARY_ITEMS = new HashSet<>(Arrays.asList("B-DAWG Keychain"));
	private final static Set<String> SMELLY_ITEMS = new HashSet<>(
			Arrays.asList("Duplicate Code", "Long Methods", "Ugly Variable Names"));
	private final static Integer MAX_ITEM_QUALITY = 50;
	private final static Integer BACKSTAGE_PASSES_10DAY_INCREASE_AMOUNT = 2;
	private final static Integer BACKSTAGE_PASSES_5DAY_INCREASE_AMOUNT = 3;

	public GildedTros(Item[] items) {
		this.items = items;
	}

	public void updateQuality() {
		for (int itemsIndex = 0; itemsIndex < items.length; itemsIndex++) {
			if (!LEGENDARY_ITEMS.contains(items[itemsIndex].name)) {
				handleNormalItem(itemsIndex);

				handleBackstagePass(itemsIndex);

				handleGoodWine(itemsIndex);

				handleSmellyItem(itemsIndex);
			}
		}
	}

	private void handleNormalItem(int itemsIndex) {
		if (!items[itemsIndex].name.equals(GOOD_WINE) && !items[itemsIndex].name.contains(BACKSTAGE_PASSES)
				&& !SMELLY_ITEMS.contains(items[itemsIndex].name)) {
			decreaseItemSellIn(itemsIndex);

			if (isExpired(itemsIndex)) {
				decreaseItemQuality(itemsIndex, 2);
			} else {
				decreaseItemQuality(itemsIndex, 1);
			}
		}
	}

	private void handleBackstagePass(int itemsIndex) {
		if (items[itemsIndex].name.contains(BACKSTAGE_PASSES)) {
			decreaseItemSellIn(itemsIndex);
			if (!isExpired(itemsIndex)) {
				if (items[itemsIndex].sellIn > 5 && items[itemsIndex].sellIn < 11) {
					increaseItemQuality(itemsIndex, BACKSTAGE_PASSES_10DAY_INCREASE_AMOUNT);
				} else if (items[itemsIndex].sellIn < 6) {
					increaseItemQuality(itemsIndex, BACKSTAGE_PASSES_5DAY_INCREASE_AMOUNT);
				} else {
					increaseItemQuality(itemsIndex, 1);
				}

			} else {
				decreaseItemQuality(itemsIndex, items[itemsIndex].quality);
			}
		}
	}

	private void handleGoodWine(int itemsIndex) {
		if (items[itemsIndex].name.equals(GOOD_WINE)) {
			decreaseItemSellIn(itemsIndex);
			increaseItemQuality(itemsIndex, 1);
		}
	}

	private void handleSmellyItem(int itemsIndex) {
		if (SMELLY_ITEMS.contains(items[itemsIndex].name)) {
			decreaseItemSellIn(itemsIndex);

			if (isExpired(itemsIndex)) {
				decreaseItemQuality(itemsIndex, 4);
			} else {
				decreaseItemQuality(itemsIndex, 2);
			}
		}
	}

	private void decreaseItemQuality(int itemsIndex, int decreaseAmount) {
		if (items[itemsIndex].quality > decreaseAmount) {
			items[itemsIndex].quality = items[itemsIndex].quality - decreaseAmount;
		} else {
			items[itemsIndex].quality = 0;
		}
	}

	private void increaseItemQuality(int itemsIndex, int increaseAmount) {
		int newItemQuality = items[itemsIndex].quality + increaseAmount;
		if (newItemQuality < MAX_ITEM_QUALITY) {
			items[itemsIndex].quality = newItemQuality;
		} else {
			items[itemsIndex].quality = MAX_ITEM_QUALITY;
		}
	}

	private boolean isExpired(int itemsIndex) {
		return items[itemsIndex].sellIn < 0;
	}

	private void decreaseItemSellIn(int itemsIndex) {
		items[itemsIndex].sellIn = items[itemsIndex].sellIn - 1;
	}
}
