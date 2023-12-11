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


def main():
    s: Scanner = Scanner("input.txt")
    while (s.has_next_line()):
        ...
        
    print(f"ANSWER: {...}")


if (__name__ == "__main__"):
    main()
