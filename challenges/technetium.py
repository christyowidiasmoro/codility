#
# MaxPathFromTheLeftTopCorner score: 100% correctness: 100% performance: 100%
# 
# Find a maximal value path in a matrix, 
# starting in the top-left corner and ending in the bottom-right corner.
#

def solution(A):
    m = len(A)
    n = len(A[0])

    S = [[0] * n for _ in range(m) ]

    S[0][0] = A[0][0]

    for i in range(1, m):
        S[i][0] = S[i-1][0] * 10 + A[i][0]

    for i in range(1, n):
        S[0][i] = S[0][i-1] * 10 + A[0][i]


    for i in range(1, m):
        for j in range(1, n):
            S[i][j] = max(S[i-1][j], S[i][j-1]) * 10 + A[i][j]
            
    return str(S[m-1][n-1])

s = solution([ 
    [9, 9, 7], 
    [9, 7, 2], 
    [6, 9, 5],
    [9, 1, 2] ])

print(s)
