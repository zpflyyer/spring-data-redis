# SDS

 1. 结构：int len, int free,char[] buf
 2. 存储文本内容时，可以兼容部分C字符串操作函数：buf[len] = '\0'
 3. 自动内存管理：空间预分配和惰性空间删除，避免了缓冲区溢出或者内存泄露问题，同时提高了内存管理效率
 4. 二进制安全
 5. 使用场景：字符串类型键的实现，AOF缓冲和客户端状态输入缓冲

-----------

# LinkedList

 1. 应用场景：列表键的实现、发布与订阅、慢查询、监视器、多个客户端状态保存和客户端输出缓冲区
 2. 结构：双向无环链表
 3. 多态：结点是void*

----------

# HashTable & Dictionary

 1. 应用场景：redis数据库本身和Hash键
 2. 结构：hashtable(使用链表解决collision,新结点插入链表表头)
 3. 多态：dict里包含特定类型的操作函数和相应类型的hashtable
 4. 索引计算：hash & sizeMask, sizeMask = size - 1,(size是2的幂：按位与能够代替取模进行index计算的前提是二进制的sizemask各位都是1，否则部分链表始终是空的)
 5. Murmurhash2算法计算hash值：冲突概率较小
 6. rehash：为ht[1]分配空间，扩展操作里分配空间size为第一个大于等于ht[0].used * 2的2的幂次方,如果是收缩操作，那么size是第一个大于等于ht[0].used 的2的幂次方
 7. 

 
     

 