/**
 * FlippingMatrix (Niobium)
 * score: 100%
 * correctness: 100%
 * performance: 100%
 * performance test: around 0.008 sec.
 */

#include <iostream>
#include <unordered_map>
#include <sstream>
#include <algorithm>
#include <stdlib.h>
#include <chrono>

int solution(std::vector<std::vector<int>> &A)
{
    int N = A.size();
    int M = A[0].size();

    if (N == 1)
        return 1;

    if (M == 1)
        return N;

    int max_value = 0;
    std::unordered_map<std::string, int> map;

    std::stringstream s0;
    std::stringstream s1;

    for (int i = 0; i < N; i++)
    {
        s0.str(std::string());
        s1.str(std::string());

        for (int j = 0; j < M; j++)
        {
            s0 << ((A[i][j] - 1) * -1);
            s1 << A[i][j];
        }

        std::string ss = s1.str();
        if (map.find(ss) == map.end())
        {
            map[ss] = 1;
        }
        else
        {
            map[ss] += 1;
        }
        max_value = std::max(max_value, map[ss]);

        ss = s0.str();
        if (map.find(ss) == map.end())
        {
            map[ss] = 1;
        }
        else
        {
            map[ss] += 1;
        }
        max_value = std::max(max_value, map[ss]);
    }

    return max_value;
}

void test(std::vector<std::vector<int>> &A)
{
    auto start_time = std::chrono::high_resolution_clock::now();

    std::cout << solution(A) << std::endl;

    auto end_time = std::chrono::high_resolution_clock::now();
    auto time = end_time - start_time;

    std::cout << "time: " << time / std::chrono::milliseconds(1) << "ms.\n";
}

int main()
{
    std::cout << "Hello World!\n";

    std::vector<std::vector<int>> A{{0, 0, 0, 0}, {0, 1, 0, 0}, {1, 0, 1, 1}};
    test(A);

    std::vector<std::vector<int>> B{{0, 1, 0, 1}, {1, 0, 1, 0}, {0, 1, 0, 1}, {1, 0, 1, 0}};
    test(B);

    std::vector<std::vector<int>> C{{1, 0}, {1, 0}};
    test(C);

    /*
    int M = 1000;
    int N = 1000;
    std::vector<std::vector<int>> R(N, std::vector<int>(M, 0));
    for(int i = 0; i < 10; i++) {
        for(int j = 0; j < 10; j++) {
            R[i][j] = rand() % 2;
        }   
    }
    std::cout << "test random" << std::endl;
    test(R);
    */
}
