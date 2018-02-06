class Node:
    '''Clase nodo'''
    def __init__(self,data):
        self.data=data
        self.left_node=None
        self.right_node=None

    def add_left(self,node):
        self.left_node=node

    def add_right(self, node):
        self.right_node=node

    def say_hi(self):
        print("Hello, how are you?")

class BinaryTree:
    '''Clase nodo tiene los atributos:
        Root= raíz del arbol binario'''

    def __init__(self,node):
        self.root=node

    def preorder_print(self):
        self.pr_preorder_print(self.root)

    def pr_preorder_print(self, node):
        if(node is None):
            return
        print(node.data)
        self.pr_preorder_print(node.left_node)
        self.pr_preorder_print(node.right_node)

    def inorder_print(self):
        self.pr_inorder_print(self.root)

    def pr_inorder_print(self, node):
        if(node is None):
            return
        self.pr_inorder_print(node.left_node)            
        print(node.data)
        self.pr_inorder_print(node.right_node)

    def posorder_print(self):
        self.pr_posorder_print(self.root)

    def pr_posorder_print(self, node):
        if(node is None):
            return
        self.pr_posorder_print(node.left_node)
        self.pr_posorder_print(node.right_node)
        print(node.data)       
    
    def add_node(self,node):
        if(node==None):
            self.root=node
            return True
        return self.pr_add_node(self.root,node)
    
    def pr_add_node(self,act_node,insert_node):
        if(act_node.data== insert_node.data):
            return False
        if(act_node.data > insert_node.data):
            if(act_node.left_node is None):
                act_node.add_left(insert_node)
                return True
            else:
                return self.pr_add_node(act_node.left_node,insert_node)
        else:
            if(act_node.right_node is None):
                act_node.add_right(insert_node)
                return True
            else:
                return self.pr_add_node(act_node.right_node,insert_node)

node = Node(500)
node2 = Node(130)
node3 = Node(800)
tree = BinaryTree(node)
tree.add_node(node2)
tree.add_node(node3)

print("Preorder")
tree.preorder_print()

print("Inorder")
tree.inorder_print()

print("Posorder")
tree.posorder_print()
