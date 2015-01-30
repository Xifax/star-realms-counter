package stealth.needle.starrealmscounter;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Star Realms counter with immersion mode.
 */
public class FullscreenActivity extends Activity {

    /**
     * Audio effects
     */
    private MediaPlayer laserSound;
    private MediaPlayer coinSound;

    /******************
     * Initialization *
     ******************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);

        /* Enable sticky immersive view */
        getWindow().getDecorView().setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        // Don't show action bar, ever
        this.getActionBar().hide();


        /* Initialize sounds */
        laserSound = MediaPlayer.create(this, R.raw.laser);
        coinSound = MediaPlayer.create(this, R.raw.coin);

        // TODO: use reflection to refactor this mess

        /* Influence */

        /* Player 1, plus HP */
        findViewById(R.id.player1_health_plus10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAmount(R.id.player1_health, 10, true);
            }
        });
        findViewById(R.id.player1_health_plus5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAmount(R.id.player1_health, 5, true);
            }
        });
        findViewById(R.id.player1_health_plus1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAmount(R.id.player1_health, 1, true);
            }
        });

        /* Player 1, minus HP */
        findViewById(R.id.player1_health_minus10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAmount(R.id.player1_health, 10, false);
            }
        });
        findViewById(R.id.player1_health_minus5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAmount(R.id.player1_health, 5, false);
            }
        });
        findViewById(R.id.player1_health_minus1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAmount(R.id.player1_health, 1, false);
            }
        });

        /* Player 2, plus HP */
        findViewById(R.id.player2_health_plus10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAmount(R.id.player2_health, 10, true);
            }
        });
        findViewById(R.id.player2_health_plus5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAmount(R.id.player2_health, 5, true);
            }
        });
        findViewById(R.id.player2_health_plus1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAmount(R.id.player2_health, 1, true);
            }
        });

        /* Player 2, minus HP */
        findViewById(R.id.player2_health_minus10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAmount(R.id.player2_health, 10, false);
            }
        });
        findViewById(R.id.player2_health_minus5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAmount(R.id.player2_health, 5, false);
            }
        });
        findViewById(R.id.player2_health_minus1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAmount(R.id.player2_health, 1, false);
            }
        });

        /* Commerce */

        /* Player 1 */
        findViewById(R.id.player1_commerce_plus1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAmount(R.id.player1_commerce, 1, true);
            }
        });
        findViewById(R.id.player1_commerce_plus2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAmount(R.id.player1_commerce, 2, true);
            }
        });
        findViewById(R.id.player1_commerce_plus4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAmount(R.id.player1_commerce, 4, true);
            }
        });
        findViewById(R.id.player1_commerce_plus8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAmount(R.id.player1_commerce, 8, true);
            }
        });
        findViewById(R.id.player1_commerce_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAmount(R.id.player1_commerce);
                coinSound.start();
            }
        });

        /* Player 2 */
        findViewById(R.id.player2_commerce_plus1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAmount(R.id.player2_commerce, 1, true);
            }
        });
        findViewById(R.id.player2_commerce_plus2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAmount(R.id.player2_commerce, 2, true);
            }
        });
        findViewById(R.id.player2_commerce_plus4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAmount(R.id.player2_commerce, 4, true);
            }
        });
        findViewById(R.id.player2_commerce_plus8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAmount(R.id.player2_commerce, 8, true);
            }
        });
        findViewById(R.id.player2_commerce_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAmount(R.id.player2_commerce);
                coinSound.start();
            }
        });

        /* Attack */

        /* Player 1 */
        setAttackListeners(R.id.player1_attack_layout, R.id.player1_attack, R.id.player2_health);

        /* Player 2 */
        setAttackListeners(R.id.player2_attack_layout, R.id.player2_attack, R.id.player1_health);

    }

    /**************************
     * UI logic and shortcuts *
     **************************/

    /**
     * Update displayed amount for specific view (influence, commerce, etc)
     * @param playerView TextView to update
     * @param amount Number to add/subtract
     * @param increment Should add or subtract
     */
    public void updateAmount(int playerView, int amount, boolean increment)  {
        TextView amountView = (TextView)findViewById(playerView);
        Integer currentAmount = Integer.parseInt(amountView.getText().toString());
        Integer updatedAmount = (increment) ? currentAmount + amount : currentAmount - amount;
        amountView.setText(String.valueOf(updatedAmount));
    }

    /**
     * Reset specified view
     * @param playerView TextView to reset
     */
    public void clearAmount(int playerView) {
        ((TextView)findViewById(playerView)).setText("0");
    }

    /**
     * Apply one player's attack to other player influence
     * @param attackField First player attack field
     * @param defenderInfluence Second player influence field
     */
    public void applyAttack(int attackField, int defenderInfluence) {
        TextView attack = (TextView)findViewById(attackField);
        TextView influence = (TextView)findViewById(defenderInfluence);
        int resultingInfluence = Integer.valueOf(influence.getText().toString()) -
                Integer.valueOf(attack.getText().toString());

        influence.setText(String.valueOf(resultingInfluence));
        attack.setText("0");

        // Pew-pew!
        laserSound.start();
    }


    /**
     * Attach actions to attack buttons
     * @param attackLayout Layout with attack buttons
     * @param playerAttack Player's attack field
     * @param playerHealth Opponent's health field
     */
    public void setAttackListeners(
            int attackLayout,
            final int playerAttack,
            final int playerHealth
    ) {
        LinearLayout playerAttackLayout = (LinearLayout)findViewById(attackLayout);

        // Iterate all layout items
        for(int i = 0; i < playerAttackLayout.getChildCount(); i++) {
            View v = playerAttackLayout.getChildAt(i);
            String name = getResources().getResourceName(v.getId());

            // Attack modifier button
            if(name.contains("plus")) {
                final int amount = Integer.valueOf(name.substring(name.length() - 1));
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        updateAmount(playerAttack, amount, true);
                    }
                });
            // Attack reset button
            } else if(name.contains("clear")) {
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clearAmount(playerAttack);
                    }
                });
            // Attack button
            } else if(name.contains("apply")) {
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        applyAttack(playerAttack, playerHealth);
                    }
                });
            }
        }

    }

    /************************************
     * Activity listeners and overrides *
     ************************************/

}
