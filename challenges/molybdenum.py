#
# LeaderSliceInc. Score: 100%, Correctness: 100%, Performance: 100%.
#

def solution(K, M, A):
    N = len(A)    

    L = []

    A[0:K] = [x+1 for x in A[0:K]]
    
    C = [0 for _ in range(M+2)]

    D = [num for num, x in enumerate(C) if x >= ((N+1) / 2)]

    L.extend(D)

    L = list(set(D))

    for n in range(N):
        C[ A[n] ] += 1

    for k in range(N - K + 1):
        if k > 0:
            C[ A[k-1] ] -= 1
            A[k-1] -= 1
            C[ A[k-1] ] += 1

            C[ A[k+K-1] ] -= 1
            A[k+K-1] += 1
            C[ A[k+K-1] ] += 1

        if C[ A[k-1] ] >= (N+1)/2:
            L.append(A[k-1])

        if C[ A[k+K-1] ] >= (N+1)/2:
            L.append(A[k+K-1])


        L = list(set(L))

    return sorted(L)
    
print(solution(3, 5, [2, 1, 3, 1, 2, 2, 3]))
print(solution(3, 5, [2, 1, 3, 1, 2, 2, 3]))
print(solution(3, 13, [1, 3, 5, 7, 9, 11, 13]))
print(solution(4, 2, [1, 2, 2, 1, 2]))
