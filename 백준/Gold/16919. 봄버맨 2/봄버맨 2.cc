#include <iostream>
#include <utility>

// macros
#define FASTIO std::ios_base::sync_with_stdio(false); std::cin.tie(NULL); std::cout.tie(NULL);
// types
using pii = std::pair<int, int>;
// constants
const int MAX_SIZE = 200;
const pii delta[4] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
// variables
int R, C, N;
char mat[MAX_SIZE][MAX_SIZE];


inline char toChar(int y, int x) { return mat[y][x] ? 'O' : '.'; }

void showMatrix(){
   for(int y = 0; y < R; ++y){
      for(int x = 0; x < C; ++x){
         std::cout << toChar(y, x);
      }
      std::cout << '\n';
   }
}
void showAllBomb(){
   for(int y = 0; y < R; ++y){
      std::cout << std::string(C, 'O') << '\n';
   }
}
void explode(){
   for(int y = 0; y < R; ++y){
      for(int x = 0; x < C; ++x){
         if(mat[y][x] != 1) continue;
         
         for(const auto& d : delta){
            int dy = y + d.second;
            int dx = x + d.first;

            if(0 <= dx && dx < C && 0 <= dy && dy < R && mat[dy][dx] == 0){
               mat[dy][dx] = 2;
            }
         }
      }
   }
   for(int y = 0; y < R; ++y){
      for(int x = 0; x < C; ++x){
         mat[y][x] = mat[y][x] ? 0 : 1;
      }
   }
}


int main() { 
   FASTIO

   std::cin >> R >> C >> N;
   for(int y = 0; y < R; ++y){
      for(int x = 0; x < C; ++x){
         std::cin >> mat[y][x];
         mat[y][x] = mat[y][x] == 'O' ? 1 : 0;
      }
   }

   if(N % 2){
      if(N == 1) { 
         // do nothing
      } else if(N % 4 == 1){
         explode(); explode();
      } else if(N % 4 == 3){
         explode();
      }
      showMatrix();
   } else{
      showAllBomb();
   }

   return 0; 
}