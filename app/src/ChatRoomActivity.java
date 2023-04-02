package com.example.messageroomtest;

public class ChatRoomActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    private TextView mChatText;
    private EditText mMessageInput;
    private Button mSendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mChatText = (TextView) findViewById(R.id.chat_text);
        mMessageInput = (EditText) findViewById(R.id.message_input);
        mSendButton = (Button) findViewById(R.id.send_button);

        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = mMessageInput.getText().toString();
                if (!message.isEmpty()) {
                    String uid = mAuth.getCurrentUser().getUid();
                    String key = mDatabase.child("messages").push().getKey();
                    ...
                    Map<String, Object> messageMap = new HashMap<>();
                    messageMap.put("uid", uid);
                    messageMap.put("text", message);
                    mDatabase.child("messages").child(key).setValue(messageMap);
                    mMessageInput.setText("");
                }
            }
        });

        mDatabase.child("messages").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String message = dataSnapshot.child("text").getValue(String.class);
                String uid = dataSnapshot.child("uid").getValue(String.class);
                if (uid.equals(mAuth.getCurrentUser().getUid())) {
                    message = "You: " + message;
                }
                mChatText.append(message + "\n");
            }

            // Other child event listener methods...

        });

        @Override
        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            String message = dataSnapshot.child("text").getValue(String.class);
            String uid = dataSnapshot.child("uid").getValue(String.class);
            if (uid.equals(mAuth.getCurrentUser().getUid())) {
                message = "You (edited): " + message;
            } else {
                message = "Someone (edited): " + message;
            }
            // Find the index of the message to be updated and replace it
            int messageIndex = getMessageIndex(dataSnapshot.getKey());
            mChatMessages.set(messageIndex, message);
            mAdapter.notifyItemChanged(messageIndex);
        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            // Find the index of the message to be removed and remove it
            int messageIndex = getMessageIndex(dataSnapshot.getKey());
            mChatMessages.remove(messageIndex);
            mAdapter.notifyItemRemoved(messageIndex);
        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            // Find the index of the message to be moved and its new index
            int oldMessageIndex = getMessageIndex(dataSnapshot.getKey());
            int newMessageIndex = getNewMessageIndex(dataSnapshot.getKey(), s);
            // Move the message to its new index
            String message = mChatMessages.remove(oldMessageIndex);
            mChatMessages.add(newMessageIndex, message);
            mAdapter.notifyItemMoved(oldMessageIndex, newMessageIndex);
        }

        try {
            Socket socket = new Socket("localhost", 8080);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String message = "Hello, server!";
        System.out.println(message);

        String response = in.readLine();
        System.out.println("Server: " + response);
    }
}
