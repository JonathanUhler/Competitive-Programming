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
        print("err", left, right)


def binary_insertion(packet: list, arr: list) -> int:
    start: int = 0
    end: int = len(arr) - 1
    while (start <= end):
        middle: int = (start + end) // 2
        comparison: bool = compare(packet, arr[middle])
        if (comparison is None): # packet == arr[middle]
            break
        elif (comparison is True): # packet < arr[middle]
            end = middle - 1
        elif (comparison is False): # packet > arr[middle]
            start = middle + 1
    arr.insert(start, packet)
    return start


def main():
    s: Scanner = Scanner("input.txt")
    packets: list = []
    while (s.has_next_line()):
        first: str = json.loads(s.next_line())
        second: str = json.loads(s.next_line())
        if (s.has_next_line()):
            s.next_line() # Clear whitespace
        packets.append((first, second))

    sorted_packets: list = []
    for packet_pair in packets:
        _ = binary_insertion(packet_pair[0], sorted_packets)
        _ = binary_insertion(packet_pair[1], sorted_packets)
    divider2_index: int = binary_insertion([[2]], sorted_packets) + 1
    divider6_index: int = binary_insertion([[6]], sorted_packets) + 1
    for packet in sorted_packets:
        print(packet)
    print(f"ANSWER: {divider2_index * divider6_index} ({divider2_index} * {divider6_index})")


if (__name__ == "__main__"):
    main()
