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

        /* Influence */
        setInfluenceListeners(R.id.player1_health_layout, R.id.player1_health);
        setInfluenceListeners(R.id.player2_health_layout, R.id.player2_health);

        /* Commerce */
        setCommerceListeners(R.id.player1_commerce_layout, R.id.player1_commerce);
        setCommerceListeners(R.id.player2_commerce_layout, R.id.player2_commerce);

        /* Attack */
        setAttackListeners(R.id.player1_attack_layout, R.id.player1_attack, R.id.player2_health);
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


    /**
     * Attach actions to commerce buttons
     * @param commerceLayout Layout with buttons
     * @param playerCommerce Player's commerce field
     */
    public void setCommerceListeners(
            int commerceLayout,
            final int playerCommerce
    ) {
        LinearLayout playerCommerceLayout = (LinearLayout)findViewById(commerceLayout);

        // Iterate all layout items
        for(int i = 0; i < playerCommerceLayout.getChildCount(); i++) {
            View v = playerCommerceLayout.getChildAt(i);
            String name = getResources().getResourceName(v.getId());

            // Commerce modifier button
            if (name.contains("plus")) {
                final int amount = Integer.valueOf(name.substring(name.length() - 1));
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        updateAmount(playerCommerce, amount, true);
                    }
                });
            // Commerce reset button
            } else if (name.contains("clear")) {
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clearAmount(playerCommerce);
                        coinSound.start();
                    }
                });
            }
        }

    }


    /**
     * Attach actions to influence buttons
     * @param influenceLayout Player influence layout
     * @param playerInfluence Player influence field
     */
    public void setInfluenceListeners(
            int influenceLayout,
            final int playerInfluence
    ) {
        LinearLayout playerInfluenceLayout = (LinearLayout)findViewById(influenceLayout);

        // Iterate all layout items
        for(int i = 0; i < playerInfluenceLayout.getChildCount(); i++) {
            View v = playerInfluenceLayout.getChildAt(i);
            String name = getResources().getResourceName(v.getId());

            // Commerce modifier button
            if (name.contains("plus")) {
                final int amount = Integer.valueOf(
                        name.substring(name.length() - 2).replaceAll("\\D+", "")
                );
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        updateAmount(playerInfluence, amount, true);
                    }
                });
                // Commerce reset button
            } else if (name.contains("minus")) {
                final int amount = Integer.valueOf(
                        name.substring(name.length() - 2).replaceAll("\\D+", "")
                );
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        updateAmount(playerInfluence, amount, false);
                    }
                });
            }
        }

    }

    /************************************
     * Activity listeners and overrides *
     ************************************/

}
