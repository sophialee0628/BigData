#!/usr/bin/python
from operator import itemgetter
from collections import defaultdict
import sys

dict_ip_count = {}

for line in sys.stdin:
    line = line.strip()
    ip, num = line.split('\t')
    try:
        num = int(num)
        dict_ip_count[ip] = dict_ip_count.get(ip, 0) + num

    except ValueError:
        pass


sorted_dict_ip_count = sorted(dict_ip_count.items(), key=itemgetter(1),reverse=True)

top= defaultdict(list)

for hr_ip,count in sorted_dict_ip_count:
	hr,ip=hr_ip.split(']')
	hr=int(hr)
	count=int(count)
	top[hr].append([ip,count])

for i in range(len(top)):
	top3=sorted(top[i],key=lambda x:x[1],reverse=True)[:3]
	print'%s' % (top3)
