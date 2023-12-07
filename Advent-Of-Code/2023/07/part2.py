from collections import Counter


class Scanner:

    def __init__(self, path: str):
        self.line = 0
        self.lines = [line.strip() for line in open(path, encoding = "utf-8").readlines()]


    def has_next_line(self) -> bool:
        return self.line < len(self.lines)


    def next_line(self) -> str:
        line: str = self.lines[self.line]
        self.line += 1
        return line



class Hand:

    strongest: str = "AAAAA"
    card_strength: dict = {"A": 14,
                           "K": 13,
                           "Q": 12,
                           "T": 10,
                           "9": 9,
                           "8": 8,
                           "7": 7,
                           "6": 6,
                           "5": 5,
                           "4": 4,
                           "3": 3,
                           "2": 2,
                           "J": 1}


    def __init__(self, hand: str):
        self.hand = hand.split()[0]
        self.bet = int(hand.split()[1])


    def get_hand(self) -> str:
        return self.hand


    def get_bet(self) -> str:
        return self.bet


    def _get_strength(self, hand: str) -> int:
        distribution: list = Counter(hand).values()
        max_distribution: int = max(distribution)

        five_of_a_kind: bool = max_distribution == 5
        four_of_a_kind: bool = max_distribution == 4
        three_of_a_kind: bool = max_distribution == 3
        two_pair: bool = Counter(distribution).get(2) == 2
        one_pair: bool = Counter(distribution).get(2) == 1
        full_house: bool = three_of_a_kind and one_pair
        high_card: bool = max_distribution == 1

        if (five_of_a_kind):  return 7
        if (four_of_a_kind):  return 6
        if (full_house):      return 5
        if (three_of_a_kind): return 4
        if (two_pair):        return 3
        if (one_pair):        return 2
        if (high_card):       return 1
        return 0


    def get_strength(self) -> int:
        if ("J" not in self.hand):
            return self._get_strength(self.hand)

        distribution: Counter = Counter(self.hand)
        del distribution["J"]
        if (len(distribution) == 0): # All jokers
            return self._get_strength(Hand.strongest)
        best_alternate: str = max(distribution, key = distribution.get)
        jokers: list = [i for i, char in enumerate(self.hand) if char == "J"]
        hand: str = self.hand
        for joker in jokers:
            hand = hand[:joker] + best_alternate + hand[joker + 1:]
        return self._get_strength(hand)


    def compare_cards(self, other: 'Hand') -> int:
        for i in range(5):
            my_card: str = self.hand[i]
            other_card: str = other.get_hand()[i]
            my_str: int = Hand.card_strength[my_card]
            other_str: int = Hand.card_strength[other_card]
            if (my_str == other_str):
                continue
            if (my_str > other_str):
                return 1
            return -1
        return 0


    def compare(self, other: 'Hand') -> int:
        my_str: int = self.get_strength()
        other_str: int = other.get_strength()

        if (my_str != other_str):
            return my_str - other_str
        return self.compare_cards(other)


    def __str__(self) -> str:
        return f"{self.hand} (str={self.get_strength()}) for ${self.bet}"


    def __repr__(self):
        return self.__str__()



def binary_insertion(start: int, end: int, hands: list, hand: Hand) -> None:
    while (start <= end):
        middle: int = (start + end) // 2
        middle_hand: Hand = hands[middle]
        comparison: int = hand.compare(middle_hand)
        if (comparison > 0):
            start = middle + 1
        elif (comparison < 0):
            end = middle - 1
        else:
            break
    hands.insert(start, hand)


def main():
    s: Scanner = Scanner("input.txt")
    hands: list = []
    while (s.has_next_line()):
        hand: Hand = Hand(s.next_line())
        binary_insertion(0, len(hands) - 1, hands, hand)

    ans: int = 0
    for i, hand in enumerate(hands):
        ans += (i + 1) * hand.get_bet()
    print(f"ANSWER: {ans}")


if (__name__ == "__main__"):
    main()
