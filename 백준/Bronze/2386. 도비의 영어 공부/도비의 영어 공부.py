def main():
    while True:
        text = input()
        if text == '#':
            return
        key = text[0]
        phrase = text[1:].lower()
        result = phrase.count(key)
        print(key, result)


if __name__ == "__main__":
    main()
