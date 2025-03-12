#include <iostream>
#include <string>
#include <map>
#include <unordered_map>

// Type definitions

// Function declarations
void Solve(int relationCount);
int FindRoot(int element);
int Merge(const std::string& leftFriend, const std::string& rightFriend);
int GetIndex(const std::string& name);
// Conxtant exprssions
constexpr int MaxFriends = 1000000;
// Global variables
int Parents[MaxFriends];
int Sizes[MaxFriends];
int Ranks[MaxFriends];
std::unordered_map<std::string, int> NameToIndexes;

int main(void) { 
    // IO setup
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(0); std::cout.tie(0);
    // Input stream
    int simulationCount;
    std::cin >> simulationCount;
    // Solution Test
    while(simulationCount-- > 0){
        int relationCount;
        std::cin >> relationCount;
        Solve(relationCount);
    }
    // Output stream


    return 0; 
}

// Take parameters for one step
// Return Solved result
void Solve(int relationCount) {
    NameToIndexes.clear();
    for(int i = 0; i < relationCount; ++i){
        std::string leftFriend, rightFriend;
        std::cin >> leftFriend >> rightFriend;

        std::cout << Merge(leftFriend, rightFriend) << "\n";
    }
}

int FindRoot(int element){
    if(element == Parents[element]){
        return element;
    }
    return Parents[element] = FindRoot(Parents[element]);
}

int Merge(const std::string& leftFriend, const std::string& rightFriend){
    int leftIndex = GetIndex(leftFriend);
    int rightIndex = GetIndex(rightFriend);

    int leftRoot = FindRoot(leftIndex);
    int rightRoot = FindRoot(rightIndex);

    if(leftRoot == rightRoot){
        return Sizes[leftRoot];
    }

    if(Ranks[leftRoot] > Ranks[rightRoot]){
        std::swap(leftRoot, rightRoot);
    }
    Parents[leftRoot] = rightRoot;
    if(Ranks[leftRoot] == Ranks[rightRoot]){
        ++Ranks[rightRoot];
    }
    return Sizes[rightRoot] += Sizes[leftRoot];
}

int GetIndex(const std::string& name){
    auto it = NameToIndexes.find(name);
    if (it == NameToIndexes.cend()) {
        // Create New elements
        int index = NameToIndexes.size();
        NameToIndexes.insert(std::make_pair(name, index));
        Parents[index] = index;
        Sizes[index] = 1;
        Ranks[index] = 0;
        return index;
    } else {
        return it->second;
    }
}