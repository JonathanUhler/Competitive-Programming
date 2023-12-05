import json
from typing import Union


class Scanner:

    def __init__(self, path: str):
        self.line = 0
        self.lines = [line.strip() for line in open(path).readlines()]

    def has_next_line(self) -> bool:
        return self.line < len(self.lines)

    def next_line(self) -> str:
        line: str = self.lines[self.line]
        self.line += 1
        return line


def compare(left: Union[int, list], right: Union[int, list]) -> bool:
    if (isinstance(left, int) and isinstance(right, int)):
        if (left == right):
            return None
        return left <= right
    elif (isinstance(left, list) and isinstance(right, list)):
        for l, r in zip(left, right):
            result: bool = compare(l, r)
            if (result is not None):
                return result
        if (len(left) == len(right)):
            return None
        return len(left) < len(right)
    elif (isinstance(left, int)):
        return compare([left], right)
    elif (isinstance(right, int)):
        return compare(left, [right])
    else:
        print("err")


def main():
    s: Scanner = Scanner("input.txt")
    packets: list = []
    while (s.has_next_line()):
        first: str = json.loads(s.next_line())
        second: str = json.loads(s.next_line())
        if (s.has_next_line()):
            s.next_line() # Clear whitespace
        packets.append((first, second))

    correct: int = 0
    for i, packet in enumerate(packets):
        first, second = packet
        if (compare(first, second)):
            correct += i + 1
            print(f"Correct order: {i + 1}")
    print(f"ANSWER: {correct}")


if (__name__ == "__main__"):
    main()
