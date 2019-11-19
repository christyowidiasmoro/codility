#
# DreamTeam. Score: 100%, Correctness: 100%, Performance: 100%.
# 
# Divide developers into two teams to maximize their total contribution.
#

def solution(A, B, F):
    N = len(A)
    Fb = int(N - F)

    total = 0
    DF = []
    DB = []
    for n in range(N):
        DF.append([n, A[n] - B[n]])
        DB.append([n, B[n] - A[n]])

    DF.sort(key=lambda x: x[1], reverse=True)
    DB.sort(key=lambda x: x[1], reverse=True)

    total = 0
    for n in range(N):
        total += A[ DF[n][0]  ]

    for n in range(Fb):
        total += DB[n][1]

    return total

A = [4, 2, 1]
B = [2, 5, 3]
F = 2
print(solution(A, B, F))

A = [7, 1, 4, 4]
B = [5, 3, 4, 3]
F = 2 
print(solution(A, B, F))

A = [5, 5, 5]
B = [5, 5, 5]
F = 1
print(solution(A, B, F))
